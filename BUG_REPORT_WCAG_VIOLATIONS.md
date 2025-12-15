# Bug Report: WCAG Accessibility Violations on Chess.com Homepage

## Bug Summary
**Critical/Serious WCAG 2.1 accessibility violations detected on Chess.com homepage**

---

## Details

- **Severity**: 🔴 **HIGH**
- **Priority**: P1 (Immediate attention required)
- **Discovered By**: Automated UI Testing Suite (Axe-core 4.7.0)
- **Date Discovered**: December 15, 2025
- **Affected Page**: https://www.chess.com/ (Homepage)
- **WCAG Level**: 2.1 Level A/AA
- **Test**: `AccessibilityTests.testWCAGComplianceHomePage`

---

## Issue Description

The Chess.com homepage has **2 critical or serious WCAG 2.1 violations** as detected by Axe-core accessibility scanner. These violations impact users with disabilities and may result in legal compliance issues.

### Impact
- **User Impact**: Users with disabilities may have difficulty accessing or using the website
- **Legal Risk**: Potential ADA (Americans with Disabilities Act) compliance violations
- **Reputation**: Accessibility issues can damage brand reputation
- **Reach**: Affects all visitors to the homepage (highest traffic page)

---

## How to Reproduce

### Prerequisites
1. Chess.com homepage (https://www.chess.com)
2. Axe-core accessibility scanner (version 4.7.0 or later)
3. Any modern web browser

### Steps to Reproduce
1. Navigate to https://www.chess.com
2. Run Axe-core accessibility scan using one of these methods:

   **Method A - Using Test Suite**:
   ```bash
   mvn test -Dtest=AccessibilityTests#testWCAGComplianceHomePage
   ```

   **Method B - Using Browser DevTools**:
   - Install axe DevTools browser extension
   - Open DevTools
   - Run "Scan All of My Page"
   - Filter by "Critical" and "Serious" issues

   **Method C - Using JavaScript Console**:
   ```javascript
   // Load Axe-core
   const script = document.createElement('script');
   script.src = 'https://cdn.jsdelivr.net/npm/axe-core@4.7.0/axe.min.js';
   document.head.appendChild(script);

   // Run scan (after script loads)
   axe.run().then(results => {
     const criticalIssues = results.violations.filter(
       v => v.impact === 'critical' || v.impact === 'serious'
     );
     console.log('Critical/Serious violations:', criticalIssues.length);
     console.table(criticalIssues);
   });
   ```

3. Observe that 2 or more critical/serious violations are detected

---

## Expected Behavior

- **Expected**: Zero critical or serious WCAG 2.1 violations
- **Actual**: 2 critical/serious violations detected

---

## Test Output

```
[FAILED] testWCAGComplianceHomePage
AssertionError: Critical/Serious WCAG violations should be 0. Found: 2

Expected: 0
Actual: 2
```

---

## Technical Details

### Violation Detection Method
- **Tool**: Axe-core 4.7.0 (industry-standard accessibility scanner)
- **Ruleset**: WCAG 2.1 Level A and AA
- **Scope**: Entire homepage DOM
- **Accuracy**: Axe-core has <1% false positive rate for critical/serious issues

### Code Location
**Test File**: `src/test/java/com/chess/tests/AccessibilityTests.java`
**Test Method**: `testWCAGComplianceHomePage` (lines 50-62)

```java
// Run Axe accessibility scan
Results results = new AxeBuilder()
    .withTags("wcag2a", "wcag2aa", "wcag21a", "wcag21aa")
    .analyze(getDriver());

// Count critical and serious violations
long criticalViolations = results.getViolations().stream()
    .filter(v -> "critical".equals(v.getImpact()) || "serious".equals(v.getImpact()))
    .count();

// Assert zero violations
Assert.assertEquals(criticalViolations, 0,
    "Critical/Serious WCAG violations should be 0. Found: " + criticalViolations);
```

---

## Investigation Needed

To identify the specific violations, run the test with detailed logging:

```bash
mvn test -Dtest=AccessibilityTests#testWCAGComplianceHomePage -DdetailedViolations=true
```

Common critical/serious WCAG violations include:
- Missing alt text on critical images
- Insufficient color contrast (< 4.5:1 for normal text)
- Missing form labels
- Invalid ARIA attributes
- Keyboard navigation issues
- Missing page language
- Empty links or buttons
- Duplicate IDs

---

## Recommended Actions

### Immediate (High Priority)
1. ✅ **Run detailed Axe-core scan** to identify specific violations
2. ✅ **Review violation details** (element selectors, failure summary, WCAG criteria)
3. ✅ **Fix critical violations** (these prevent users from accessing content)
4. ✅ **Fix serious violations** (these create significant barriers)

### Short Term
5. ✅ **Verify fixes** with automated and manual testing
6. ✅ **Test with screen readers** (NVDA, JAWS, VoiceOver)
7. ✅ **Test keyboard navigation** (Tab, Enter, Space, Arrow keys)
8. ✅ **Re-run Axe scan** to confirm zero critical/serious violations

### Long Term
9. 📊 **Integrate Axe-core** into CI/CD pipeline
10. 📊 **Scan all pages**, not just homepage
11. 📊 **Address moderate/minor violations**
12. 📊 **Conduct full accessibility audit** with real users

---

## Additional Context

### Why This Matters

1. **Legal Compliance**:
   - ADA Title III requires websites to be accessible
   - Recent lawsuits against major websites (Domino's, Target, etc.)
   - Average settlement: $20,000 - $400,000

2. **User Impact**:
   - 1 in 4 adults in US have a disability
   - 15% of global population (1 billion people)
   - Growing market segment with $1.9 trillion in disposable income

3. **SEO Benefits**:
   - Google prioritizes accessible websites
   - Better semantic HTML improves search rankings
   - Improved user experience metrics

4. **Business Impact**:
   - Increased market reach
   - Improved brand reputation
   - Reduced legal risk
   - Better UX for all users (not just disabled users)

---

## Related Documentation

- [WCAG 2.1 Guidelines](https://www.w3.org/WAI/WCAG21/quickref/)
- [Axe-core Rules](https://github.com/dequelabs/axe-core/blob/develop/doc/rule-descriptions.md)
- [WebAIM Contrast Checker](https://webaim.org/resources/contrastchecker/)
- [W3C Accessibility Guidelines](https://www.w3.org/WAI/standards-guidelines/)

---

## Test Evidence

**Test Status**: ❌ **FAILING** (correctly identifying real issues)
**Pass Rate Before**: 8/9 accessibility tests passing (89%)
**Pass Rate After Fix**: Expected 9/9 (100%)

**Note**: This is not a test failure - this is the test working correctly and discovering real accessibility issues that need to be fixed on the website.

---

## Assignee

**Recommended Assignment**:
- Frontend Development Team (for fixes)
- QA Team (for verification)
- Accessibility Specialist (for audit)

---

## Labels

`accessibility`, `wcag`, `compliance`, `critical`, `homepage`, `legal-risk`, `bug`

---

**Report Generated**: December 15, 2025
**Reported By**: Automated UI Testing Suite
**Status**: 🔴 **OPEN - Awaiting Investigation**
