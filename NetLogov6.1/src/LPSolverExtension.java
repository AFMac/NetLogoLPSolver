/**
 * LPSolverExtension is the wrapper for the extension.
 *
 * @author AFMac
 * @version 3.0.0
 * Updated for NetLogo v6.1 by Charles Staelin
 */
package org.nlogo.extensions.lpsolver;

import java.io.File;
import org.nlogo.api.Context;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.PrimitiveManager;
import org.nlogo.api.JavaLibraryPath;

public class LPSolverExtension extends org.nlogo.api.DefaultClassManager {

    private static org.nlogo.workspace.ExtensionManager em;

    // runs when the extension is loaded to make sure all the needed .jar, 
    // .dll and/or .so files can be found.

    @Override
    public void runOnce(org.nlogo.api.ExtensionManager em)
            throws org.nlogo.api.ExtensionException {
        
        // We don't use this, yet.
        LPSolverExtension.em = (org.nlogo.workspace.ExtensionManager) em;

        // find out what operating system we have.
        String operatingSystem = System.getProperty("os.name").toUpperCase();
        // All the OS's need java.lang.library to be set. We find out where 
        // the extension is installed and set java.lang.path to that directory.
        String userExtensionDirectory
                = org.nlogo.api.FileIO.perUserDir("extensions"
                        + File.separator + "lpsolver", false);
        File toAdd = new File(userExtensionDirectory);
        // this code uses the JavaLibraryPath.add method from NetLogo.
        try {
            JavaLibraryPath.add(toAdd);
        } catch (Exception ex) {
            throw new ExtensionException(
                    "Error adding extension path to java.library.path.\n", ex);
        }

        // if it's not the Windows OS; there is more to do.
        if (!operatingSystem.startsWith("WIN")) {
            if (operatingSystem.startsWith("MAC")) {
                // don't yet know what to do here.
            } else {
                /** we assume Linux. Ideally we would like to alter 
                 * LD_LIBRARY_PATH to point to the directory where the 
                 * extension is installed. However, Ubuntu (at least) does 
                 * not allow the user to set this environment variable in 
                * any way that persists, so we are reduced to copying the 
                * liblpsolve55.so shared library to where Linux will look 
                * without being told.  /usr/lib is one such place. It would 
                * be nice if we could do the copy here, but doing so requires
                * administrator privileges.
                */
                // See if liblpsolve55.so is where it needs to be.
                File soLib = new File("/usr/lib/liblpsolve55.so");
                if (!soLib.exists()) {
                    throw new ExtensionException(
                            "The dynamic link library 'liblpsolve55.so' must be "
                            + "placed in '/usr/lib'. Please refer to the "
                            + "LPSolver installation instructions for Linux.");
                }
            }
        }
    }

    @Override
    public void load(PrimitiveManager primitiveManager) {
        primitiveManager.addPrimitive("max", new LPMaximize());
        primitiveManager.addPrimitive("min", new LPMinimize());
        primitiveManager.addPrimitive("dualsens", new DualSens());
    }

    // This is used for debugging only.
    public static void writeToNetLogo(String mssg, Boolean toOutputArea,
            Context context) throws ExtensionException {
        try {
            context.workspace().outputObject(mssg, null, true, true,
                    (toOutputArea)
                            ? org.nlogo.api.OutputDestinationJ.OUTPUT_AREA()
                            : org.nlogo.api.OutputDestinationJ.NORMAL());
        } catch (LogoException e) {
            throw new ExtensionException(e);
        }
    }
}
