INSTALLATION:

The lpsolver extension requires multiple library files in specific places in order to function. 

In later versions of NetLogo (issues with ver 5.3.1 were recently addressed), the directory structure has changed.  In these cases, utilize the following:

1:  In your NetLogo[version]/app/extensions folder, create an "lpsolver" folder
2:  Add "lpsolver.jar" and "lpsolve55j.jar" (utilize appropriate 32-bit or 64-bit versions based on your NL install)
3:  Under NetLogo[version], add "lpsolve55.dll", "lpsolve55j.dll" and "lpsolve55j.jar" (32-bit or 64-bit)

For earlier versions of NetLogo, folder structure may be different.  See below for alternative file placement

1:  In your NetLogo[version] folder, under "extensions", create an "lpsolver" folder.
2:  Add "lpsolver.jar" and "lpsolve55j.jar" 
3:  Under NetLogo[version]/lib, add "lpsolve55.dll" and lpsolve55j.dll"


Note on licensing:

lpsolve (http://lpsolve.sourceforge.net/5.5/) was released under the GNU Lesser GPL.  Details can be found at 
http://lpsolve.sourceforge.net/5.5/LGPL.htm.

Questions/comments/request for additional functionality:  amackenzieus[at]yahoo.com

Copyright 2014 Adam MacKenzie
