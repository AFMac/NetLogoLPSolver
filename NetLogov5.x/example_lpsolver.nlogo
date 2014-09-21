extensions [lpsolver]

;Copyright 2014 Adam MacKenzie
;    
;    The code contained herein is free software; you can redistribute it and/or
;    modify it under the terms of the GNU General Public License as published by 
;    the Free Software Foundation version 3 of the License, or (at your option) 
;    any later version. This library is distributed in the hope that it will be useful,
;    but WITHOUT ANY WARRANTY; without even the implied warranty of
;    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
;    General Public License for more details.
;
;    Should you desire a copy of the GNU General Public License, you can visit 
;    http://www.gnu.org/licenses/licenses.html, or write to the Free Software
;    Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA

;    The developer can be contacted at:  amackenzieus [ at ] yahoo.com

to go
  ;solver must be fed constraints, objective function, and callout for any integer variables
  ;Callout is as follows:
  
  ;Assuming the general form:
  ; Maximize c1 x1 + c2 x2 + ...
  ; a11 x1 + a12 x2 + ... <= b1
  ; a21 x1 + a22 x2 + ... <= b2
  ; .
  ; .
  ; .
  ; (Signs need not be less than or equal to...see below)
  ; Variables (x1, x2, ...) considered to be non-negative
  
  ;Constraints will be provided as:
  ;[[constraint 1] [constraint 2] [constraint 3] ...]
  ;[[a11 a12 ... sign right-hand-side] [a21 a22 ...] ... ]
  ; For sign, the following values must be used:
  ; <= .............  1
  ; =  .............  3
  ; >= .............  2
  
  ;Objective function provided as:
  ;[c1 c2 ... ]
  
  ;Additional list must be provided to designate individual variables (x1, x2, ...)
  ;as integer, binary or neither.  This takes the form of a simple list, with index 
  ;referenced to the variable.  Syntax is as follows:
  ;0 = no specific constraints
  ;1 = variable is an integer variable
  ;2 = variable is a binary variable
  ; Therefore:
  ; [2 1 0] would translate to c1 binary, c2 integer, c3 non-integer
  
  ;Final syntax for this is:
  ;lptest [# of variables] [constraints] [objective fn] [binary constraints]
  ;See below for a simple example.  In "standard" form, this problem would be:
  
  ; Maximize 143x1 + 60 x2
  ; such that
  ; 120x1 + 210x2 <= 15,000
  ; 110x1 + 30x2 <= 4000
  ; x1 + x2 <= 75
  ; (neither x1 or x2 must be integer valued)
  
  ;execution will return a list containing the problem solution and each variable's optimum
  ;value in the form:
  ; [solution x1-value x2-value ...]
  
  
  let numvar 3
  
  let con [
            [1 2 1 1 430]
            [3 0 2 1 460]
            [1 4 0 1 420]
          ]
  
  let obj [3 2 5]
  
  let bins [0 0]

  print lpsolver:max numvar con obj bins
  print lpsolver:min numvar con obj bins        ;;for this problem, the answer is trivial, but this is the syntax for a minimization problem
  print lpsolver:dualsens numvar con obj bins   ;;as of September 2014, the sensitivity functionality is still being developed.  No guarantees!

end
 
