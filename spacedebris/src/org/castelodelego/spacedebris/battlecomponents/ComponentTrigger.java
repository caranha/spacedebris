package org.castelodelego.spacedebris.battlecomponents;

/**
 * This component indicates that some change in the entity will take place after a certain amount of time has passed.
 * When the timer runs out, there is a list of components to be added and to be removed that should be processed.
 * 
 * @author caranha
 *
 */
public class ComponentTrigger implements ComponentInterface {

	public float countdown; // timer
	public ComponentInterface[] addlist; // components to be added to the entity once the trigger runs out;
	public ComponentInterface[] removelist; // components to be removed from the entity once the trigger runs out;

	public ComponentTrigger (float time, ComponentInterface[] add, ComponentInterface[] remove)
	{
		countdown = time;
		addlist = add;
		removelist = remove;
		if (addlist == null)
			addlist = new ComponentInterface[0];
		if (removelist == null)
			removelist = new ComponentInterface[0];
	}
	
	@Override
	public ComponentType getType() {
		return ComponentType.COMP_TIMER;		
	}

	@Override
	public ComponentInterface copy() {
		ComponentInterface[] remcopy = new ComponentInterface[removelist.length];
		ComponentInterface[] addcopy = new ComponentInterface[removelist.length];
		for (int i = 0; i < addlist.length; i++)
			addcopy[i] = addlist[i].copy();
		for (int i = 0; i < removelist.length; i++)
			remcopy[i] = removelist[i].copy();
		
		return (new ComponentTrigger(countdown, addcopy, remcopy));
	}

	@Override
	public void dispose() {
		for (int i = 0; i < addlist.length; i++)
		{
			addlist[i] = null;
		}
		for (int i = 0; i < removelist.length; i++)
		{
			removelist[i] = null;
		}
	}

}
