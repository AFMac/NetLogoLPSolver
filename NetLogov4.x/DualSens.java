import lpsolve.*;
import org.nlogo.api.*;
import java.util.*;
import java.lang.*;
import java.lang.Double;

public class DualSens extends DefaultReporter
{

    public Syntax getSyntax() 
    {
	   int[] right = new int[] { Syntax.TYPE_NUMBER, Syntax.TYPE_LIST, Syntax.TYPE_LIST, Syntax.TYPE_LIST};
	   //int ret = Syntax.TYPE_NUMBER;
	   int ret = Syntax.TYPE_LIST;
	   return Syntax.reporterSyntax( right, ret );
    }
    
    public Object report(Argument args[], Context context)  throws ExtensionException 
    {
	   try {
	   	   //specify vars for the passed parameters
		   int numVars = 0;
		   int i = 0;
		   LogoList objfn = new LogoList();
		   LogoList LPparams = new LogoList();
		   LogoList intfn = new LogoList();   
		   String con = "";
		   String obj = "";
		   double y = 0;
		   double varVals = 0;
		   
		   numVars = args[0].getIntValue();
		   LPparams= args[1].getList();
		   objfn = args[2].getList();
		   intfn = args[3].getList();

		   Iterator it = 	LPparams.iterator();

		   /* Move through each sub-list of the passed list
		   Individual lists should spell out a particular parameter of the desired LP
		   [ [obj func] [constraint 1] [contstraint 2] [...] ]
		   */
		   
		   //generate the LP with number of variables passed by user
		   LpSolve solver = LpSolve.makeLp(0, numVars);

		   while (it.hasNext())
		   {
			   LogoList intList = (LogoList) it.next();
			   con = "";
			   for(i =1; i <= numVars ;i++) {
				   y = ((Double)intList.get(i-1));
				   con += " " + y;
			   }
			   
			   solver.strAddConstraint(con, ((Double)intList.get(i-1)).intValue(),((Double)intList.get(i)));	   
		   }
		   //add in objective function
		   
		   for(i=1;i<= objfn.size();i++) {
		   	   y = ((Double)objfn.get(i-1));
			   obj += " " + y;
			}
		   solver.strSetObjFn(obj);
		   
		   //opportunity to set variables as integer
		   for(i = 1;i <= intfn.size() ;i++) {
			   y = ((Double)intfn.get(i-1));
			   if (y == 1) {
				   solver.setInt(i, true);
			   } else {
				   solver.setInt(i, false);
			   }
		   }
		   
		   
		   // reduce verbosity, set as max
		   solver.setVerbose(3);
		   solver.setMaxim();

		   // solve the problem
		   solver.solve();
		   		   
		   //get sensitivity data
		   int cntr = solver.getNorigRows() + numVars;
		   double [][] sens = new double [cntr][cntr];
		   sens = solver.getPtrSensitivityRhs();
		
		LogoList rtnList = new LogoList();
   		   
		for (i = 0;i < sens[0].length; i++) {
			rtnList.lput(sens[0][i]);
		}
		   

		 //  y = solver.getObjective();
		   solver.deleteLp();
		   
		   return rtnList;

	   }	   
		   catch (LogoException e) {
	    throw new ExtensionException ( e.getMessage());
		   }
		   catch (LpSolveException e) {
			   throw new ExtensionException ( e.toString());
		   }	   
		   
	   }
	   
    }
		   
