# HomeLink Plugins
These are some plugins for HomeLink. All of them may not work as intended but some might.

## Strict Current "Guidelines"
 - The "Main" Class that will be invoked must be in the package "homelink" and the classname MUST be "plugin". The full path will result in "homelink.plugin"
 - A onEnabled function is recommended as it is pretty much the "Constructor" of the class.
 - It is recommended to have "super.onDisable()" in the onDisable function as it unregisters and cleans up a few variables. But this can be done by hand.
