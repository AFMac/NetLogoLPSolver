### INSTALLATION INSTRUCTIONS for LPSolver v3.0.0:

This version of the `lpsolver` extension is compatible with NetLogo 6.1, and requires multiple library files in order to function. Installation instructions for Windows and Linux are given below. Instructions for the Macintosh are still being developed.

**For Windows:**

The best way to install the extensions is through the NetLogo Extension Manager that is integrated into NetLogo 6.1. Simply place the line<br/>
    `extensions [lpsolver]` <br/>
at the beginning of your NetLogo model, or add `lpsolver` to the extensions already listed. When you press the "Check" button, the Extension Manager will say that the `lpsolver` extension is not installed and will ask if you would like NetLogo to download it for you. Choose "Yes" and NetLogo will download and install the extension and the necessary libraries in the hidden directory 
`AppData\Roaming\NetLogo\6.1\extensions\lpsolver`
under your home directory. That is all you need to do to use the extension. (In order to look at this directory, you may need to ask the File Explorer to show hidden files.)

**For Linux:**

The best way to install the extensions is through the NetLogo Extension Manager that is integrated into NetLogo 6.1. Simply place the line<br/>
    `extensions [lpsolver]` <br/> 
at the beginning of your NetLogo model, or add `lpsolver` to the extensions already listed. When you press the "Check" button, the Extension Manager will say that the `lpsolver` extension is not installed and will ask if you would like NetLogo to download it for you. Choose "Yes" and NetLogo will download and install the extension and the necessary libraries in the hidden directory 
`.netlogo/6.1/extensions/lpsolver`
under your home directory. (In order to look at this directory, you may need to ask the file manager, e.g., Nautilaus in Ubuntu, to show system files.) In that directory you will see the file liblpsolve55.so. This file must be copied to `/usr/lib`. There is a shell file, Copy_liblpsolve55.sh that shows how this can be done, and will do if for you if executed in the Terminal. You will need administrator privileges.

**Additional Information**

In both Windows and Linux, there is in the directory where `lpsolver` was installed an example NetLogo model. There may also be additional documentation.

**Note on licensing:**

lpsolve (http://lpsolve.sourceforge.net/5.5/) was released under the GNU Lesser GPL.  Details can be found at 
http://lpsolve.sourceforge.net/5.5/LGPL.htm.

Questions/comments/request for additional functionality:  amackenzieus[at]yahoo.com

Copyright 2019 Adam MacKenzie
