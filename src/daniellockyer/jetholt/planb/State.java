package daniellockyer.jetholt.planb;

public enum State {
	OUTSIDE(new String[] { "wall_outside" }), //
	FOYER(new String[] { "wall_foyer" }), //
	OFFICES(new String[] { "wall_offices" }), //
	PREVAULT(new String[] { "wall_prevault" }), //
	VAULT(new String[] { "wall_vault", "gold_pile_1", "gold_pile_2", "gold_pile_3", "gold_pile_4", "gold_pile_5", "gold_pile_6", "gold_pile_7", "gold_pile_8", "gold_pile_9", "gold_pile_10",
			"gold_pile_11", "gold_pile_12" });

	private final String[] show;

	private State(String[] show) {
		this.show = show;
	}

	public String[] layers() {
		return this.show;
	}
}
