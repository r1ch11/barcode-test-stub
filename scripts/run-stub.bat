@echo off
REM ----------------------------------------------------------------------
REM --                                                                  --
REM -- Use to run a manual test using the barcode scanner stub.         --
REM --                                                                  --
REM -- Assumption: Java is in the local file system and %JAVA_HOME%\bin --
REM --             is set in the %PATH%.                                --
REM --                                                                  --
REM -- Usage:                                                           --
REM --  (1) Open an empty text file named "test.txt" in Notepad and     --
REM --      make sure it is not minimized.                              --
REM --  (2) Run this script.                                            --
REM --  (3) Observe that the barcode (defined in this script) is        --
REM --      written to the "test.txt" file.                             --
REM --                                                                  --
REM ----------------------------------------------------------------------
java -version
set SCRIPT_DIR=%~dp0
set WINDOW_TITLE="test.txt - Notepad"
set BARCODE="01-87625-0"
java -jar %SCRIPT_DIR%Stub.jar %WINDOW_TITLE% %BARCODE%
pause
