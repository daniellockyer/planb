package daniellockyer.jetholt.planb;

public enum State {
	OUTSIDE(new String[] { "wall_outside" }), //
	FOYER(new String[] { "wall_foyer" }), //
	OFFICES(new String[] { "wall_offices" }), //
	PREVAULT(new String[] { "wall_prevault" }), //
	VAULT(new String[] { "wall_vault" });

	private final String[] show;

	private State(String[] show) {
		this.show = show;
	}

	public String[] layers() {
		return this.show;
	}
}
