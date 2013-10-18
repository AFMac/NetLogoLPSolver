import lpsolve.*;
import org.nlogo.api.*;
import java.util.*;
import java.lang.*;
import java.lang.Double;

public class lpmin extends DefaultReporter
{

    public Syntax getSyntax() 
    {
	   int[] right = new int[] { Syntax.NumberType(), Syntax.ListType(), Syntax.ListType(), Syntax.ListType()};
	   //int ret = Syntax.TYPE_NUMBER;
	   int ret = Syntax.ListType();
	   return Syntax.reporterSyntax( right, ret );
    }
    
    public Object report(Argument args[], Context context)  throws ExtensionException 
    {
	   try {
		   //specify vars for the passed parameters
		   int numVars = 0;
		   int i = 0;
		   LogoListBuilder objfn = new LogoListBuilder();
		   LogoListBuilder LPparams = new LogoListBuilder();
		   LogoListBuilder intfn = new LogoListBuilder();
		   String con = "";
		   String obj = "";
		   double y = 0;
		   double varVals = 0;
		   
		   numVars = args[0].getIntValue();
		   LPparams.add(args[1].get());
		   objfn.add(args[2].get());
		   intfn.add(args[3].get());


		   Object argX = args[1].get();
		   LogoList LogListLPparams = LogoList.fromJava((LogoList) argX);
		   
		   argX = args[2].get();
		   LogoList LogListObjFn = LogoList.fromJava((LogoList) argX);
		   
		   argX = args[3].get();
		   LogoList LogListIntFn = LogoList.fromJava((LogoList) argX);
		   
		   
		   
		   //Iterator it = LPparams.iterator();
		   Iterator it = LogListLPparams.iterator();

		   /* Move through each sub-list of the passed list
		   Individual lists should spell out a particular parameter of the desired LP
		   [ [obj func] [constraint 1] [contstraint 2] [...] ]
		   */
		   
		   //generate the LP with number of variables passed by user
		   LpSolve solver = LpSolve.makeLp(0, numVars);

		   LogoListBuilder rtnList = new LogoListBuilder();
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
		   
		   for(i=1;i<= LogListObjFn.size();i++) {
		   	   y = ((Double)LogListObjFn.get(i-1));
			   obj += " " + y;
			}
		   solver.strSetObjFn(obj);
		   
		   //opportunity to set variables as integer
		   for(i = 1;i <= LogListIntFn.size() ;i++) {
			   y = ((Double)LogListIntFn.get(i-1));
			   if (y == 1) {
				   solver.setInt(i, true);
			   } 
			   else if (y == 2) {
			   	   solver.setBinary(i, true);
			   }
			   else {
				   solver.setInt(i, false);
			   }
		   }
		   
		   
		   // reduce verbosity, set as max
		   solver.setVerbose(3);
		   solver.setMinim();

		   // solve the problem
		   solver.solve();
		   
		   //get solution info
		   double[] var = solver.getPtrVariables();
		   
		   for (i = 0;i < var.length; i++) {
			   rtnList.add(var[i]);
		   }

//		   y = solver.getObjective();
//		   y = ((Double)LogListLPparams.get(0));
		   solver.deleteLp();

		   LogoList finalRtnList = rtnList.toLogoList();
		   finalRtnList = finalRtnList.fput(y);
		   
//		   rtnList.add(y);  //need some way to designate this at the front of the list
		   
		   
//		   return rtnList.toLogoList();
		   return finalRtnList;

	   }	   
		   catch (LogoException e) {
	    throw new ExtensionException ( e.getMessage());
		   }
		   catch (LpSolveException e) {
			   throw new ExtensionException ( e.toString());
		   }	   
		   
	   }
	   
    }
		   
