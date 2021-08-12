
/*
 * authors: PHAM Quang Dung (dungkhmt@gmail.com)
 * date: 27/09/2015
 */

package library.localsearch.domainspecific.vehiclerouting.vrp.neighborhoodexploration;

import library.localsearch.domainspecific.vehiclerouting.vrp.VRManager;
import library.localsearch.domainspecific.vehiclerouting.vrp.VarRoutesVR;
import library.localsearch.domainspecific.vehiclerouting.vrp.entities.LexMultiValues;
import library.localsearch.domainspecific.vehiclerouting.vrp.entities.Point;
import library.localsearch.domainspecific.vehiclerouting.vrp.functions.LexMultiFunctions;
import library.localsearch.domainspecific.vehiclerouting.vrp.moves.IVRMove;
import library.localsearch.domainspecific.vehiclerouting.vrp.moves.OrOptMove2;
import library.localsearch.domainspecific.vehiclerouting.vrp.search.ISearch;
import library.localsearch.domainspecific.vehiclerouting.vrp.search.Neighborhood;

public class FirstImprovementOrOptMove2Explorer implements INeighborhoodExplorer {
	private VRManager mgr;
	private VarRoutesVR XR;
	private ISearch search;
	private LexMultiFunctions F;
	private LexMultiValues bestValue;
	
	public FirstImprovementOrOptMove2Explorer(VarRoutesVR XR, LexMultiFunctions F) {
		this.XR = XR;
		this.F = F;
		this.mgr = XR.getVRManager();
	}
	
	public FirstImprovementOrOptMove2Explorer(ISearch search, VRManager mgr, LexMultiFunctions F){
		this.search = search;
		this.mgr = mgr;
		this.XR = mgr.getVarRoutesVR();
		this.F = F;
		this.bestValue = search.getIncumbentValue();
	}
	
	public void exploreNeighborhood(Neighborhood N, LexMultiValues bestEval) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= XR.getNbRoutes(); i++) {
			if(N.size() > 0) break;
			for (int j = 1; j <= XR.getNbRoutes(); j++) {
				if(N.size() > 0) break;
				if (i != j) {
					for (Point x1 = XR.next(XR.getStartingPointOfRoute(i)); x1 != XR.getTerminatingPointOfRoute(i); x1 = XR.next(x1)) {
						if(N.size() > 0) break;
						for (Point x2 = XR.next(x1); x2 != XR.getTerminatingPointOfRoute(i); x2 = XR.next(x2)) {
							if(N.size() > 0) break;
							for (Point y = XR.getStartingPointOfRoute(j); y != XR.getTerminatingPointOfRoute(j); y = XR.next(y)) {
								if(N.size() > 0) break;
								if (XR.checkPerformOrOptMove(x1, x2, y)) {
									LexMultiValues eval = F.evaluateOrOptMove2(x1, x2, y);
									if (eval.lt(bestEval)){
										N.add(new OrOptMove2(mgr, eval, x1, x2, y));
									}
								}
							}
						}
					}
				}
			}
		}
	}

	public String name(){
		return "FirstImprovementOrOptMove2Explorer";
	}
	public void performMove(IVRMove m){
		//DO NOTHING
	}
}
