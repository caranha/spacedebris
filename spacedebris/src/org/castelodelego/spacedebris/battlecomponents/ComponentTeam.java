package org.castelodelego.spacedebris.battlecomponents;

public class ComponentTeam implements ComponentInterface {

	public int team;
	
	public ComponentTeam(int t)
	{
		team = t;
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.COMP_TEAM;
	}

	@Override
	public ComponentInterface copy() {
		return (new ComponentTeam(team));
	}

	@Override
	public void dispose() {
		// intentionally left blank
	}

}
