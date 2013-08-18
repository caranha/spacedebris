package org.castelodelego.spacedebris.battlecomponents;

/**
 * This static class contains the labels for the components
 * @author caranha
 *
 */
public enum ComponentType {
		COMP_POS, // indicates that this component has a position in the battle space
		COMP_DIR, // indicates that this component is moving, and adds a velocity
		COMP_TEAM, // indicates that this component has a team, and interacts with opposite teams upon collision
		COMP_ANIM, // indicates that this component has an animation
		COMP_COLLBOX, // an AABB
		
		COMP_KILL, // indicates that this entity must be removed from the game
		COMP_TIMER, // after the timer is done, adds and removes the list of components
}
