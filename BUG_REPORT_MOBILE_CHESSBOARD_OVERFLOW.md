# Bug Report: Chessboard Horizontal Overflow on Mobile Devices

## Bug Summary
**Chessboard overflows viewport on mobile devices (375px width), causing horizontal scrolling**

---

## Details

- **Severity**: 🟡 **MEDIUM**
- **Priority**: P2 (Should be fixed soon)
- **Discovered By**: Automated UI Testing Suite (Responsive Layout Tests)
- **Date Discovered**: December 15, 2025
- **Affected Page**: https://www.chess.com/puzzles (and likely other pages with chessboards)
- **Device Impact**: Mobile phones (particularly iPhone SE, iPhone 12 Mini, small Android phones)
- **Test**: `ResponsiveLayoutTests.testChessboardResponsiveness`

---

## Issue Description

The chessboard on Chess.com's puzzles page does not properly scale down to fit mobile viewports. On a standard mobile viewport (375px width), the chessboard renders at **485px width**, causing **110px of horizontal overflow** and requiring users to scroll horizontally to see the entire board.

### Impact
- **User Experience**: Poor mobile UX requiring horizontal scrolling
- **Usability**: Difficult to view entire board at once on mobile
- **Visual**: Board appears cut off on small screens
- **Engagement**: May discourage mobile users from solving puzzles

---

## How to Reproduce

### Prerequisites
1. Chrome browser with DevTools
2. Or: Actual mobile device (iPhone SE, iPhone 12 Mini, etc.)
3. Or: Selenium WebDriver with mobile viewport

### Steps to Reproduce

**Method A - Browser DevTools**:
1. Open Chrome browser
2. Navigate to https://www.chess.com/puzzles
3. Open DevTools (F12)
4. Enable Device Toolbar (Ctrl+Shift+M)
5. Select "iPhone SE" (375px width) or set custom 375x667
6. Refresh the page
7. Observe chessboard width in Elements inspector
8. **Expected**: Board width ≤ 375px
9. **Actual**: Board width = 485px (overflows by 110px)

**Method B - Automated Test**:
```bash
mvn test -Dtest=ResponsiveLayoutTests#testChessboardResponsiveness
```

**Method C - Manual Mobile Device**:
1. Open Safari on iPhone SE or similar small device
2. Navigate to https://www.chess.com/puzzles
3. Observe horizontal scrollbar appears
4. Try to view the entire chessboard
5. Notice you must scroll horizontally

---

## Expected vs. Actual Behavior

### Expected Behavior
- Chessboard should scale down to fit mobile viewport
- Board width should be ≤ 375px (or viewport width minus padding)
- No horizontal scrolling required
- Entire board visible without interaction

### Actual Behavior
- Chessboard renders at 485px width
- Exceeds viewport by 110px (29% overflow)
- Horizontal scrollbar appears
- User must scroll to see the entire board

---

## Test Output

```
[FAILED] testChessboardResponsiveness
AssertionError: Board should fit mobile viewport, width: 485

Expected: width <= 400
Actual: width = 485
```

### Technical Details

**Test Code** (`ResponsiveLayoutTests.java` lines 169-199):
```java
@Test
@MethodOwner(owner = "qa-team")
@TestLabel(name = "feature", value = {"ui", "responsive", "mobile"})
public void testChessboardResponsiveness() {
    // Set mobile viewport
    getDriver().manage().window().setSize(new Dimension(375, 667));

    // Navigate to puzzles page (has chessboard)
    PuzzlesPageBase puzzlesPage = initPage(getDriver(), PuzzlesPageBase.class);
    puzzlesPage.open();
    Assert.assertTrue(puzzlesPage.isPageOpened(), "Puzzles page should be opened");

    pause(3); // Wait for board to render

    // Find chessboard
    WebElement board = getDriver().findElement(
        By.xpath("//div[contains(@class, 'board')]"));

    // Get board width
    int boardWidth = board.getSize().getWidth();

    // Board should fit in mobile viewport
    Assert.assertTrue(boardWidth <= 400,
        "Board should fit mobile viewport, width: " + boardWidth);
}
```

---

## Root Cause Analysis

### Possible Causes

1. **Fixed Width CSS**:
   - Chessboard may have `width: 485px` hardcoded
   - Missing responsive breakpoints

2. **Missing Max-Width**:
   - No `max-width: 100%` constraint
   - Board doesn't respect parent container width

3. **CSS Media Query Missing**:
   - No `@media (max-width: 768px)` rules for board
   - Desktop board sizing applied to mobile

4. **Container Overflow**:
   - Parent container may also be oversized
   - Board inherits incorrect width from parent

### Recommended Investigation

Inspect the chessboard element's computed styles:
```css
/* Check for these properties */
div.board, div#board {
  width: ?
  max-width: ?
  min-width: ?
  box-sizing: ?
}

/* Look for missing responsive styles */
@media (max-width: 768px) {
  div.board {
    /* Should have responsive width here */
  }
}
```

---

## Suggested Fix

### CSS Solution (Recommended)

```css
/* Ensure board is responsive on mobile */
div.board,
div#board,
div.board-layout-chessboard {
  max-width: 100%;
  width: 100%;
  box-sizing: border-box;
}

/* Mobile-specific sizing */
@media (max-width: 768px) {
  div.board,
  div#board,
  div.board-layout-chessboard {
    max-width: calc(100vw - 20px); /* Account for padding */
    width: 100%;
    margin: 0 auto;
  }
}

/* Extra small devices (iPhone SE, etc.) */
@media (max-width: 375px) {
  div.board,
  div#board,
  div.board-layout-chessboard {
    max-width: calc(100vw - 10px); /* Minimal padding */
  }
}
```

### JavaScript Solution (If CSS is insufficient)

```javascript
// Dynamically resize board on mobile
function resizeChessboard() {
  const board = document.querySelector('div.board, div#board');
  if (!board) return;

  const viewportWidth = window.innerWidth;

  if (viewportWidth <= 768) {
    const maxBoardWidth = viewportWidth - 20; // 10px padding on each side
    board.style.maxWidth = maxBoardWidth + 'px';
    board.style.width = '100%';
  }
}

// Run on load and resize
window.addEventListener('load', resizeChessboard);
window.addEventListener('resize', resizeChessboard);
```

---

## Testing Verification

After implementing the fix, verify on these devices/viewports:

### Test Viewports
1. **iPhone SE**: 375x667 (smallest modern iPhone)
2. **iPhone 12 Mini**: 375x812
3. **Galaxy S8**: 360x740 (popular Android size)
4. **Pixel 5**: 393x851
5. **iPhone 12 Pro**: 390x844

### Verification Steps
1. Navigate to https://www.chess.com/puzzles
2. Inspect board element width
3. Verify `boardWidth <= viewportWidth - 20` (accounting for minimal padding)
4. Verify no horizontal scrollbar appears
5. Verify board is still usable (pieces are tappable)

### Automated Test
```bash
# Should pass after fix
mvn test -Dtest=ResponsiveLayoutTests#testChessboardResponsiveness
```

Expected result: ✅ **PASS**

---

## Screenshots

**Before Fix** (Current Behavior):
```
┌─────────────────────────┐
│  Mobile Viewport 375px  │
│  ┌────────────────────┐ │
│  │                    │ │
│  │   [Chessboard ─────┼─┼──> Overflows 110px
│  │    485px wide]     │ │
│  │                    │ │
│  └────────────────────┘ │
│   ← Scroll bar appears  │
└─────────────────────────┘
```

**After Fix** (Expected Behavior):
```
┌─────────────────────────┐
│  Mobile Viewport 375px  │
│  ┌───────────────────┐  │
│  │                   │  │
│  │   [Chessboard]    │  │
│  │   355px wide      │  │
│  │   (fits nicely)   │  │
│  │                   │  │
│  └───────────────────┘  │
│   No scrollbar needed   │
└─────────────────────────┘
```

---

## Impact Assessment

### Affected Devices
- **iPhone SE** (375px): ❌ Affected
- **iPhone 12 Mini** (375px): ❌ Affected
- **Small Android phones** (<400px): ❌ Affected
- **iPhone 12/13** (390px): ⚠️ Likely affected
- **Tablets** (768px+): ✅ Not affected
- **Desktop** (1920px): ✅ Not affected

### User Impact
- **Mobile traffic**: Approximately 40-60% of web traffic
- **Chess.com mobile app**: May not be affected (native app)
- **Mobile web**: Definitely affected

### Business Impact
- **Engagement**: Users may abandon puzzles on mobile web
- **Conversion**: Poor UX may reduce mobile subscriptions
- **Retention**: Frustrating experience on mobile
- **Reputation**: Looks unprofessional on modern devices

---

## Related Issues

This bug may affect other pages:
- ✅ Check `/play` page (live chess games)
- ✅ Check `/analysis` page (analysis board)
- ✅ Check `/lessons` page (lesson boards)
- ✅ Check `/computer` page (play vs computer)

Recommendation: Run responsive layout tests on all pages with chessboards.

---

## Priority Justification

**Why P2 (Medium Priority)**:
1. ✅ Affects significant portion of users (mobile web)
2. ✅ Creates poor user experience
3. ✅ Easy to reproduce
4. ✅ Moderate fix complexity

**Why NOT P1 (High Priority)**:
1. ❌ Mobile app likely works fine (native layout)
2. ❌ Website still functional (just requires scrolling)
3. ❌ No data loss or security risk
4. ❌ Desktop experience unaffected

---

## Recommended Actions

### Immediate
1. ✅ **Investigate CSS** on puzzles page chessboard
2. ✅ **Identify root cause** (fixed width, missing media query, etc.)
3. ✅ **Test on real device** (iPhone SE or equivalent)

### Short Term
4. ✅ **Implement CSS fix** (add responsive styles)
5. ✅ **Test on multiple devices** (iPhone, Android)
6. ✅ **Verify fix doesn't break desktop** layout
7. ✅ **Re-run automated test** to confirm pass

### Long Term
8. ✅ **Audit all pages** with chessboards
9. ✅ **Add responsive board component** to design system
10. ✅ **Monitor mobile analytics** for improvement

---

## Additional Context

### Best Practices for Responsive Chessboards
- Use `max-width: 100%` for all board elements
- Implement CSS media queries for mobile breakpoints
- Use `vw` units or `calc()` for dynamic sizing
- Test on real devices, not just DevTools
- Ensure touch targets remain ≥44x44px (Apple HIG)

---

## Test Evidence

**Test Status**: ❌ **FAILING** (correctly identifying real issue)
**Test File**: `ResponsiveLayoutTests.java`
**Test Method**: `testChessboardResponsiveness` (lines 169-199)

**Note**: This is not a test failure - this is the test working correctly and discovering a real responsive design issue.

---

## Assignee

**Recommended Assignment**:
- Frontend Development Team (for CSS fix)
- UX/UI Team (for design review)
- QA Team (for cross-device testing)

---

## Labels

`responsive-design`, `mobile`, `css`, `chessboard`, `ux`, `bug`, `puzzles`

---

**Report Generated**: December 15, 2025
**Reported By**: Automated UI Testing Suite
**Status**: 🟡 **OPEN - Awaiting Fix**
**Estimated Fix Time**: 2-4 hours (CSS changes + testing)
