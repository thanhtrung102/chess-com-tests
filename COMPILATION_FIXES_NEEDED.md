# Compilation Fixes Needed for UI Tests

The UI test files have 26 compilation errors that need manual fixes. These are orphaned lines from LOGGER statement removal.

## Quick Fix Guide

For each error, you need to either:
1. **Remove** the orphaned line completely, OR
2. **Complete** the incomplete Assert statement

## VisualRegressionTests.java (12 errors)

### Line 77-79: Incomplete Assert.assertFalse
**Current (broken)**:
```java
Assert.assertFalse(backgroundColor.equals("rgba(0, 0, 0, 0)"),

// Verify text color is set
```

**Fix**: Add missing closing parenthesis and message
```java
Assert.assertFalse(backgroundColor.equals("rgba(0, 0, 0, 0)"),
    "Play button background should not be transparent");

// Verify text color is set
```

### Line 86: Orphaned string
**Current (broken)**:
```java
                   ", Color: " + color + ", Font size: " + fontSize);
```

**Fix**: Remove this entire line (it's a leftover from LOGGER.info)

### Line 112-119: Multiple issues
**Current (broken)**:
```java
Assert.assertTrue(fontSize >= 18,

// Font weight should indicate bold (400+ for normal, 700 for bold)
int fontWeight = Integer.parseInt(headingFontWeight);
Assert.assertTrue(fontWeight >= 400,
    "Heading font weight should be at least 400, actual: " + fontWeight);

                   ", Weight: " + headingFontWeight);
```

**Fix**:
```java
Assert.assertTrue(fontSize >= 18,
    "Heading font size should be at least 18px, actual: " + fontSize + "px");

// Font weight should indicate bold (400+ for normal, 700 for bold)
int fontWeight = Integer.parseInt(headingFontWeight);
Assert.assertTrue(fontWeight >= 400,
    "Heading font weight should be at least 400, actual: " + fontWeight);
```

### Line 144, 149: Incomplete Assert statements
**Line 144**: Remove orphaned line after Assert.assertNotEquals
**Line 171**: Remove orphaned line after Assert.assertTrue

### Line 205, 215: Orphaned strings
Remove these entire lines:
```java
                   ", Aspect ratio: " + aspectRatio);
```

### Line 246, 256: Orphaned strings
Remove lines starting with lots of spaces + quotes

## AccessibilityTests.java (9 errors)

### Line 53, 64: Orphaned strings in testWCAGComplianceHomePage
**Lines 52-54**: Empty for loop and orphaned string
```java
for (Rule violation : violations) {
}
```
**Fix**: Remove the empty loop body content

**Line 64**: Remove orphaned string starting with spaces

### Line 106, 110: testImagesHaveAltText
**Line 109**: Incomplete Assert.assertTrue
```java
Assert.assertTrue(percentageWithoutAlt < 50,
```
**Fix**: Add message
```java
Assert.assertTrue(percentageWithoutAlt < 50,
    "More than 50% of images lack alt text: " + percentageWithoutAlt + "%");
```

### Line 131, 136: testKeyboardNavigation
**Line 135**: Incomplete Assert.assertTrue
**Fix**: Add message
```java
Assert.assertTrue(focusableElements.size() > 0,
    "Page should have focusable elements for keyboard navigation");
```

### Line 204, 215-216: testFormAccessibility
**Line 215**: Incomplete Assert.assertTrue
**Fix**: Add message
```java
Assert.assertTrue(percentageWithLabels >= 70,
    "At least 70% of inputs should have labels. Actual: " + percentageWithLabels + "%");
```

### Line 250, 279: testARIARoles
**Line 249**: Incomplete Assert.assertTrue
**Fix**: Add message
```java
Assert.assertTrue(hasLandmarks,
    "Page should use semantic HTML5 elements or ARIA landmark roles");
```
**Line 251-252**: Remove orphaned string lines

### Line 310, 327: testHeadingHierarchy
**Line 326**: Incomplete Assert.assertTrue
**Fix**: Add message
```java
Assert.assertTrue(allHeadings.size() > 0,
    "Page should have heading elements for proper document structure");
```

### Line 344, 367-368: testColorNotOnlyIndicator
**Line 367**: Incomplete Assert.assertTrue
**Fix**: Add message
```java
Assert.assertTrue(errorText.length() > 0,
    "Error should convey information via text, not just color");
```

## ResponsiveLayoutTests.java (5 errors)

### Line 275, 285: testContentReflow
**Line 284**: Orphaned string
Remove line starting with spaces + quotes

### Line 305, 316: testTouchFriendlyElements
**Line 316**: Orphaned string in if block
Remove line

### Line 329, 340-341: testViewportMetaTag
**Line 340**: Incomplete Assert.assertTrue
**Fix**: Add message
```java
Assert.assertTrue(viewportMetas.size() > 0,
    "Viewport meta tag should be present for responsive design");
```

## Automated Fix Script

Since the errors are systematic, here's a sed script to fix most of them:

```bash
cd /c/Users/admin/chess-com-tests/src/test/java/com/chess/tests

# Remove all lines that are just orphaned strings (start with spaces + quotes/operators)
for file in VisualRegressionTests.java ResponsiveLayoutTests.java AccessibilityTests.java; do
    # Remove standalone orphaned concatenation lines
    sed -i '/^[[:space:]]\{10,\}"/d' "$file"
    sed -i '/^[[:space:]]\{10,\}+/d' "$file"
done
```

## Manual Verification Needed

After running automated fixes, manually check:
1. All Assert statements have closing parenthesis and semicolon
2. All Assert statements have proper error messages
3. No empty blocks (empty if/for/while bodies)
4. Compile with `mvn test-compile` to verify

## Expected Result

After all fixes:
- 0 compilation errors
- 62 test cases ready to run
- All 26 UI tests compilable

Run this to verify:
```bash
mvn test-compile
```

Should show:
```
[INFO] BUILD SUCCESS
```

Then run UI tests:
```bash
mvn test -Dsuite=ui-tests
```
