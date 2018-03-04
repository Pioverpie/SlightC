#  ![SlightC](https://raw.githubusercontent.com/Pioverpie/SlightC/master/images/Untitled3.png)
A statically typed language project created for Hack FSU 5.

## Introduction
SlightC is a C-like language with some conviences and conventions borrowed from Python, Ruby and other interpreted languages. While syntacially similar to C, SlightC does deviate in certain areas, we go into further detail about those differences below. SlightC is a superset of C as it transpiles directly into C code during compilation.

## Table Of Contents
[Quick Start](#quick_start)<br/>
[Variables](#variables)<br/>
[Conditionals and Control Flow](#conditionals_and_control_flow)<br/>
[Input and Output](#input_and_output)<br/>
[Functions](#functions)<br/>
[Examples](#examples)<br/>
[Conclusion](#conclusion)<br/>

## <a name="quick_start"></a>Quick Start
Download or clone the repository to an empty folder.

To run the main program, the [Visual Studio 2017](https://www.visualstudio.com/downloads/) C compiler is required to be installed.

Or if you don't want to use the Visual Studio compiler and want to use a custom C complier, then you have to manually edit the batch script to run the command of the c compiler of your choice where the VS compiler is run.

Once installed, its time to make your first SlightC program.

Here is a one line program for Hello World:<br/>
`output("Hello World!")`

To run your program, type `javac Main.java`.
If it runs correctly then run `compileScripts.bat YourProgramName.slc` into the command line of eith CMD or Powershell. Note: your program will automatically run after being compiled.

If done correctly your program should output:<br/>
`"Hello World!"`

### <a name="variables"></a>Variables
Variables in C require explicit data type declaration when declaring a variable. SlightC uses type interfacing to determine the data type of a variable. Under the hood, variables are stored as primitive c data types.

a C variable declaration:<br/>
`int x = 5;`

the equivalent declaration in SlightC:<br/>
`x is 5;`

Notice that SlightC uses the keyword `is` for assignment instead of the typical `=` operator

### <a name="conditionals_and_control_flow"></a>Conditionals and Control Flow
Most control flow loops and conditional statements share the identical syntax to C, with the exception of for loops<br/>

In C, a typical for loop may look something like below:<br/>
```
for(int i = 0; i < 10; i++) {
    // statements
}
```

In SlightC, for loops take on a syntax more similar to Python than C, although there are still some key differences:<br/>
```
from 0 to 10 {
    // statements
}
```
### <a name="input_and_output"></a>Input and Output
Input and output work similar to how Python handles IO.

For output the syntax in SlightC is:<br/>
`output(x)`

The equivalent in Python would be:<br/>
`printf(x)` if x is a string and `printf("%d", x)` if x is an int.

For input syntax is identical to the C equivalent. This includes cross compatibilty with C libraries such as `stdio.h`

### <a name="functions"></a>Functions
In SlightC, functions are syntactically similar to to function definitions in Javascript. Compared to C the only difference is that instead of specifying the return type the keyword `function` is used when defining a function.

```
function myFunction(x, y, z) {
    // statements
}
```

To return a value from the function use the keyword `return` just like in C

### <a name="examples"></a>Examples
A Hello World example:<br/>
```
output("Hello World!");
```

Use of nested For loops:<br/>
```
x is 3
y is 4

from 0 to x {
    output("Outer")
    from 0 to y {
        output("Inner")
    }
}
```

Euclids Greatest Common Devisor (GCD) algorithm:<br/>
```
function gcd(m, n) {
    if (m == 0 && n == 0)
        return -1;

    if (m < 0) m is -m;
    if (n < 0) n is -n;

    while (n) {
        r is m % n;
        m is n;
        n is r;
    }
    return m;
}

output(gcd(25, 10));
```
## <a name="conclusion"></a>Conclusion
SlightC is a language that combines the ease of Python with the speed C. It represents the best of both worlds, allowing users to develop programs with a simplified syntax reminiscent of interpreted languages, yet that runs with the speed of a compiled program.
