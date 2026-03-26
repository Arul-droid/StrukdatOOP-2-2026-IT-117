package src;

/**
 * Enum genre game.
 * Fokus pada RPG/Action dan turunannya.
 */
public enum Genre {
    ACTION_RPG("Action RPG"),
    JRPG("JRPG"),
    HACK_AND_SLASH("Hack and Slash"),
    OPEN_WORLD_RPG("Open World RPG"),
    SOULS_LIKE("Souls-like"),
    ACTION_ADVENTURE("Action Adventure");

    private final String label;

    Genre(String label) { this.label = label; }

    public String getLabel() { return label; }

    @Override
    public String toString() { return label; }
}
