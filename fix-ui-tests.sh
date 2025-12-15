#!/bin/bash
# Script to fix all compilation errors in UI test files
# Run this from the chess-com-tests directory

echo "Fixing UI test compilation errors..."

# Get the project root directory
PROJECT_ROOT="$(pwd)"

cd src/test/java/com/chess/tests

# Backup files first
echo "Creating backups..."
cp VisualRegressionTests.java VisualRegressionTests.java.bak
cp ResponsiveLayoutTests.java ResponsiveLayoutTests.java.bak
cp AccessibilityTests.java AccessibilityTests.java.bak

# Fix VisualRegressionTests.java
echo "Fixing VisualRegressionTests.java..."

# Line 77: Complete Assert.assertFalse
sed -i '77s/Assert.assertFalse(backgroundColor.equals("rgba(0, 0, 0, 0)"),/Assert.assertFalse(backgroundColor.equals("rgba(0, 0, 0, 0)"),\n            "Play button background should not be transparent");/' VisualRegressionTests.java

# Line 87: Remove orphaned string
sed -i '87d' VisualRegressionTests.java

# Line 112: Complete Assert.assertTrue for fontSize
sed -i '112s/Assert.assertTrue(fontSize >= 18,/Assert.assertTrue(fontSize >= 18,\n                "Heading font size should be at least 18px, actual: " + fontSize + "px");/' VisualRegressionTests.java

# Line 119: Remove orphaned string
sed -i '119d' VisualRegressionTests.java

# Line 148: Complete Assert.assertNotEquals
sed -i '148s/Assert.assertNotEquals(bodyColor, bodyBgColor,/Assert.assertNotEquals(bodyColor, bodyBgColor,\n            "Text color should differ from background color for readability");/' VisualRegressionTests.java

# Line 177: Complete Assert.assertTrue
sed -i '177s/Assert.assertTrue(isInteractive,/Assert.assertTrue(isInteractive,\n            "Interactive elements should indicate clickability (cursor: pointer or enabled)");/' VisualRegressionTests.java

# Line 215: Remove orphaned string
sed -i '215d' VisualRegressionTests.java

# Lines 256-257: Remove orphaned strings
sed -i '256,257d' VisualRegressionTests.java

# Fix AccessibilityTests.java
echo "Fixing AccessibilityTests.java..."

# Line 64: Remove orphaned string
sed -i '64d' AccessibilityTests.java

# Line 109: Complete Assert.assertTrue
sed -i '109s/Assert.assertTrue(percentageWithoutAlt < 50,/Assert.assertTrue(percentageWithoutAlt < 50,\n            "More than 50% of images lack alt text: " + percentageWithoutAlt + "%");/' AccessibilityTests.java

# Line 135: Complete Assert.assertTrue
sed -i '135s/Assert.assertTrue(focusableElements.size() > 0,/Assert.assertTrue(focusableElements.size() > 0,\n            "Page should have focusable elements for keyboard navigation");/' AccessibilityTests.java

# Line 215: Complete Assert.assertTrue
sed -i '215s/Assert.assertTrue(percentageWithLabels >= 70,/Assert.assertTrue(percentageWithLabels >= 70,\n            "At least 70% of inputs should have labels. Actual: " + percentageWithLabels + "%");/' AccessibilityTests.java

# Line 249: Complete Assert.assertTrue
sed -i '249s/Assert.assertTrue(hasLandmarks,/Assert.assertTrue(hasLandmarks,\n            "Page should use semantic HTML5 elements or ARIA landmark roles");/' AccessibilityTests.java

# Lines 251-252: Remove orphaned strings
sed -i '251,252d' AccessibilityTests.java

# Line 326: Complete Assert.assertTrue
sed -i '326s/Assert.assertTrue(allHeadings.size() > 0,/Assert.assertTrue(allHeadings.size() > 0,\n            "Page should have heading elements for proper document structure");/' AccessibilityTests.java

# Line 367: Complete Assert.assertTrue
sed -i '367s/Assert.assertTrue(errorText.length() > 0,/Assert.assertTrue(errorText.length() > 0,\n                "Error should convey information via text, not just color");/' AccessibilityTests.java

# Fix ResponsiveLayoutTests.java
echo "Fixing ResponsiveLayoutTests.java..."

# Line 285: Remove orphaned string
sed -i '285d' ResponsiveLayoutTests.java

# Line 316: Remove orphaned string (in if block)
sed -i '316d' ResponsiveLayoutTests.java

# Line 340: Complete Assert.assertTrue
sed -i '340s/Assert.assertTrue(viewportMetas.size() > 0,/Assert.assertTrue(viewportMetas.size() > 0,\n            "Viewport meta tag should be present for responsive design");/' ResponsiveLayoutTests.java

echo "Done! Attempting to compile..."
cd "$PROJECT_ROOT"
mvn test-compile

if [ $? -eq 0 ]; then
    echo ""
    echo "========================================="
    echo "SUCCESS! All compilation errors fixed!"
    echo "========================================="
    echo ""
    echo "You can now run UI tests with:"
    echo "  mvn test -Dsuite=ui-tests"
    echo ""
    echo "Backup files saved as:"
    echo "  - VisualRegressionTests.java.bak"
    echo "  - ResponsiveLayoutTests.java.bak"
    echo "  - AccessibilityTests.java.bak"
else
    echo ""
    echo "========================================="
    echo "Compilation still has errors."
    echo "========================================="
    echo ""
    echo "You may need to manually review the files."
    echo "Backup files are available to restore if needed."
fi
