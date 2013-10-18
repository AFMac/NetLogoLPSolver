import org.nlogo.api.*;

public class lptest extends DefaultClassManager {

	
    public void load(PrimitiveManager primitiveManager) 
    {
    	primitiveManager.addPrimitive("max", new lp());
	primitiveManager.addPrimitive("min", new lpmin());
    	primitiveManager.addPrimitive("dualsens", new DualSens());
    }
}
