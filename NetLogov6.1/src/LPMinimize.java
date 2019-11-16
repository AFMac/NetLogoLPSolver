/**
 * LPMinimize is a slight modification from class lp and instantiates and 
 * solves a minimization problem passed by the user.
 * 
 * @author AFMac 
 * @version 3.0.0
 * Updated for NetLogo v6.1 by Charles Staelin
 */
package org.nlogo.extensions.lpsolver;

import lpsolve.*;
import org.nlogo.api.Argument;
import org.nlogo.api.Context;
import org.nlogo.api.Reporter;
import org.nlogo.api.ExtensionException;
import org.nlogo.api.LogoException;
import org.nlogo.api.LogoListBuilder;
import org.nlogo.core.Syntax;
import org.nlogo.core.SyntaxJ;
import org.nlogo.core.LogoList;
import java.util.Iterator;

public class LPMinimize implements Reporter
{

    @Override
     public Syntax getSyntax() 
    {
	   int[] right = new int[] { Syntax.NumberType(), Syntax.ListType(), 
               Syntax.ListType(), Syntax.ListType()};
	   int ret = Syntax.ListType();
	   return SyntaxJ.reporterSyntax( right, ret );
    }
    
    @Override
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

		   LogoList LogListLPparams = args[1].getList();
		   LogoList LogListObjFn = args[2].getList();
		   LogoList LogListIntFn = args[3].getList();
                   Iterator<?> it = LogListLPparams.javaIterator();

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

		   y = solver.getObjective();
		   solver.deleteLp();

		   LogoList finalRtnList = rtnList.toLogoList();
		   finalRtnList = finalRtnList.fput(y);
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
		   
