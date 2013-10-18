import org.nlogo.api.*;

public class lptest extends DefaultClassManager {

	
    public void load(PrimitiveManager primitiveManager) 
    {
    	primitiveManager.addPrimitive("lptest", new lp());
	primitiveManager.addPrimitive("dualSens", new DualSens());
    }
}
