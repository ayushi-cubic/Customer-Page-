@echo off
REM Quick Start Script for Customer Test Automation

echo ================================================
echo Customer Management Test Automation Framework
echo ================================================
echo.

REM Check if Maven is installed
where mvn >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Maven is not installed or not in PATH
    echo Please install Maven and try again
    pause
    exit /b 1
)

echo Maven found: 
call mvn -version
echo.

REM Check if Java is installed
where java >nul 2>nul
if %ERRORLEVEL% NEQ 0 (
    echo ERROR: Java is not installed or not in PATH
    echo Please install Java JDK 17+ and try again
    pause
    exit /b 1
)

echo Java found:
java -version
echo.

REM Display menu
:menu
echo ================================================
echo Select an option:
echo ================================================
echo 1. Clean and Install Dependencies
echo 2. Run Tests (Chrome - Default)
echo 3. Run Tests (Firefox)
echo 4. Run Tests (Edge)
echo 5. Run Tests (Chrome Headless)
echo 6. View Last Test Report
echo 7. Clean Test Output
echo 8. Exit
echo.
set /p choice="Enter your choice (1-8): "

if "%choice%"=="1" goto install
if "%choice%"=="2" goto run_chrome
if "%choice%"=="3" goto run_firefox
if "%choice%"=="4" goto run_edge
if "%choice%"=="5" goto run_headless
if "%choice%"=="6" goto view_report
if "%choice%"=="7" goto clean
if "%choice%"=="8" goto exit

echo Invalid choice. Please try again.
goto menu

:install
echo.
echo Installing dependencies...
call mvn clean install -DskipTests
echo.
echo Installation complete!
pause
goto menu

:run_chrome
echo.
echo Running tests on Chrome...
call mvn clean test -Dbrowser=chrome
echo.
echo Tests completed! Check reports in test-output/cucumber-reports/
pause
goto menu

:run_firefox
echo.
echo Running tests on Firefox...
call mvn clean test -Dbrowser=firefox
echo.
echo Tests completed! Check reports in test-output/cucumber-reports/
pause
goto menu

:run_edge
echo.
echo Running tests on Edge...
call mvn clean test -Dbrowser=edge
echo.
echo Tests completed! Check reports in test-output/cucumber-reports/
pause
goto menu

:run_headless
echo.
echo Running tests on Chrome (Headless)...
call mvn clean test -Dbrowser=chrome
echo.
echo Tests completed! Check reports in test-output/cucumber-reports/
pause
goto menu

:view_report
echo.
echo Opening test report...
if exist "test-output\cucumber-reports\cucumber.html" (
    start "" "test-output\cucumber-reports\cucumber.html"
) else (
    echo No report found. Please run tests first.
)
pause
goto menu

:clean
echo.
echo Cleaning test output...
if exist "test-output" (
    rmdir /s /q test-output
    echo Test output cleaned!
) else (
    echo Test output directory does not exist.
)
if exist "target" (
    rmdir /s /q target
    echo Target directory cleaned!
)
pause
goto menu

:exit
echo.
echo Thank you for using Customer Test Automation Framework!
echo.
exit /b 0
