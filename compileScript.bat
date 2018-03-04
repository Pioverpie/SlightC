@echo off
set home=%~dp0
call "C:\Program Files (x86)\Microsoft Visual Studio\2017\Community\VC\Auxiliary\Build\vcvars32.bat" x86_amd64
call "C:\Program Files\Microsoft Visual Studio 2008\VC\vcvarsall.bat" x86_amd64
call "C:\Program Files (x86)\Microsoft Visual Studio 14.0\VC\vcvarsall.bat
cd "%home%"
echo "Starting Conversion"
set tbc=%~f1
java Main "%~f1"
echo "Conversion Complete"
cl "%tbc:~0,-4%.c"
rem del "%tbc:~0,-4%.c"
del "%tbc:~0,-4%.obj"
"%tbc:~0,-4%.exe"
echo[
echo[
pause