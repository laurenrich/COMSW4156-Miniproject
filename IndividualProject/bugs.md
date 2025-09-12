# Bug Fixes Documentation

This document tracks all bugs found and fixed in the codebase.

## Bugs Found and Fixed

### Bug #1: Missing Return Statement in getLanguage()
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Method `getLanguage()` declared to return `String` but has no return statement
- **Fix**: Add `return language;` statement

### Bug #2: Type Cast Error in equals() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Attempting to assign `Object` to `Book` without casting
- **Fix**: Add explicit cast `(Book) obj`

### Bug #3: Naming Convention Violations
- **Files**: Multiple files
- **Issue**: Class names `BOOK` and `MockAPIService` violate Google Checkstyle rules
- **Fix**: Renamed to `Book` and `MockApiService`

### Bug #4: Incorrect Assignment in setShelvingLocation()
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Method assigns string literal `"shelvingLocation"` instead of the parameter value
- **Fix**: Change `this.shelvingLocation = "shelvingLocation";` to `this.shelvingLocation = shelvingLocation;`

### Bug #5: Incorrect Logic in returnCopy() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Method checks `if (returnDates.isEmpty())` which prevents processing when there are return dates
- **Fix**: Change condition to `if (!returnDates.isEmpty())`

### Bug #6: Incorrect Decrement in checkoutCopy() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Method decrements `amountOfTimesCheckedOut` instead of incrementing when checking out
- **Fix**: Change `amountOfTimesCheckedOut--` to `amountOfTimesCheckedOut++`

### Bug #7: Incorrect Return Values in deleteCopy() Method
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Method returns `false` when copy is successfully deleted and `true` when deletion fails
- **Fix**: Swap return values - return `true` on successful deletion, `false` on failure

### Bug #8: Unused Private Field
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Private field `bookmarks` is declared but never used
- **Fix**: Remove the unused field or implement functionality to use it

### Bug #9: Empty Method Body Without Comment
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Method `addCopy()` has empty body
- **Fix**: Uncommented comment within body

### Bug #10: Empty Catch Block
- **File**: `src/main/java/dev/coms4156/project/individualproject/service/MockApiService.java`
- **Issue**: Catch block is empty
- **Fix**: Add error logging or proper exception handling

### Bug #11: Expression fix
- **File**: `src/main/java/dev/coms4156/project/individualproject/service/MockApiService.java`
- **Issue**: Statement `this.books = this.books;` assigns variable to itself
- **Fix**: Assign `this.books` to `tmpBooks`

### Bug #15: Missing hashCode() Override
- **File**: `src/main/java/dev/coms4156/project/individualproject/model/Book.java`
- **Issue**: Class implements Comparable but doesn't override hashCode() method
- **Fix**: Add hashCode() method implementation

### Bug #16: ArrayList Usage
- **Files**: MockApiService.java, BookUnitTests.java
- **Issue**: Using concrete implementation `ArrayList` instead of interface `List`
- **Fix**: Change `ArrayList<Book>` to `List<Book>`
