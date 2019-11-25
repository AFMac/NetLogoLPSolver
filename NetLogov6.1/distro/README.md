### INSTALLATION INSTRUCTIONS for LPSolver v3.0.0:

This version of the `lpsolver` extension is compatible with NetLogo 6.1 and requires multiple library files in order to function. Installation instructions for Windows and Linux are given below.  Unfortunately, there is no version of the underlying Sourceforge LPSolve software compatible with the Macintosh version of NetLogo 6.1.

**For both Windows and Linux:**

The best way to install the extensions is through the NetLogo Extension Manager that is integrated into NetLogo 6.1. Simply place the line<br/>
    `extensions [lpsolver]` <br/>
at the beginning of your NetLogo model, or add `lpsolver` to the extensions already listed. When you press the "Check" button, the Extension Manager will say that the `lpsolver` extension is not installed and will ask if you would like NetLogo to download it for you. Choose "Yes" and NetLogo will download and install both the extension and the necessary libraries in a directory specific to each OS.

**For Windows:**

 The extension and its libraries will be installed in a hidden directory 
`AppData\Roaming\NetLogo\6.1\extensions\lpsolver`
under your home directory.  (In order to look at this directory, you may need to ask File Explorer to show hidden files.) There is one further step. The path to the directory must be included in your user or system environment's PATH variable. If you run the extension without having done this, you will get an error message that includes the exact path.  It will look something like<br/> `C:\Users\<username>\AppData\Roaming\NetLogo\6.1\extensions\lpsolver` 

**For Linux:**

The extension and its libraries will be installed in the hidden directory 
`.netlogo/6.1/extensions/lpsolver`
under your home directory. (In order to look at this directory, you may need to ask the file manager, e.g., Nautilaus in Ubuntu, to show system files.) In that directory you will see the file liblpsolve55.so. This file must be copied to `/usr/lib`. There is a shell file, Copy_liblpsolve55.sh that shows how this can be done, and will do if for you if executed in the Terminal. You will need administrator privileges.

**Additional Information**

In both Windows and Linux, there is under the directory where `lpsolver` was installed an `Example` directory with an example NetLogo model that shows how the extension is used. There may also be additional documentation.

**Note on licensing:**

lpsolve (http://lpsolve.sourceforge.net/5.5/) was released under the GNU Lesser GPL.  Details can be found at 
http://lpsolve.sourceforge.net/5.5/LGPL.htm.

Questions/comments/request for additional functionality:  amackenzieus[at]yahoo.com

Copyright 2019 Adam MacKenzie
