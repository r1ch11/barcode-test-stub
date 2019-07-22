@echo off

java -version

set SCRIPT_DIR=%~dp0

rem start javaw -jar %SCRIPT_DIR%Stub.jar

set WINDOW_TITLE="test.txt - Notepad"

set BARCODE="01-87625-0"

java -jar %SCRIPT_DIR%Stub.jar %WINDOW_TITLE% %BARCODE%

pause