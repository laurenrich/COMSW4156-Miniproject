# Bug Fixes Documentation

This document tracks all bugs found and fixed in the codebase.

## Bug Finding Tools Used
- **PMD**: Static code analysis tool
- **Manual Code Review**: Javadoc and function name analysis
- **Compilation Errors**: Java compiler error messages

## Installation and Usage Commands

### PMD Installation and Usage
```bash
# PMD is included as a Maven plugin, no separate installation needed
./mvnw pmd:check
```

## Bugs Found and Fixed

### Bug #1: Missing Return Statement in getLanguage()
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Line**: 183
- **Issue**: Method `getLanguage()` declared to return `String` but has no return statement
- **Fix**: Add `return language;` statement
- **Type**: Compilation Error

### Bug #2: Type Cast Error in equals() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Line**: 269
- **Issue**: Attempting to assign `Object` to `Book` without casting
- **Fix**: Add explicit cast `(Book) obj`
- **Type**: Compilation Error

### Bug #3: Naming Convention Violations
- **Files**: Multiple files
- **Issue**: Class names `BOOK` and `MockAPIService` violate Google Checkstyle rules
- **Fix**: Renamed to `Book` and `MockApiService`
- **Type**: Code Style Violation

### Bug #4: Incorrect Assignment in setShelvingLocation()
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Line**: 195
- **Issue**: Method assigns string literal `"shelvingLocation"` instead of the parameter value
- **Fix**: Change `this.shelvingLocation = "shelvingLocation";` to `this.shelvingLocation = shelvingLocation;`
- **Type**: Logic Error

### Bug #5: Incorrect Logic in returnCopy() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Line**: 151
- **Issue**: Method checks `if (returnDates.isEmpty())` which prevents processing when there are return dates
- **Fix**: Change condition to `if (!returnDates.isEmpty())`
- **Type**: Logic Error

### Bug #6: Incorrect Decrement in checkoutCopy() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Line**: 130
- **Issue**: Method decrements `amountOfTimesCheckedOut` instead of incrementing when checking out
- **Fix**: Change `amountOfTimesCheckedOut--` to `amountOfTimesCheckedOut++`
- **Type**: Logic Error

### Bug #7: Incorrect Return Values in deleteCopy() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Line**: 110
- **Issue**: Method returns `false` when copy is successfully deleted and `true` when deletion fails
- **Fix**: Swap return values - return `true` on successful deletion, `false` on failure
- **Type**: Logic Error


