package org.hugoandrade.euro2016.backend.model.parser;

import com.google.gson.JsonObject;

import org.hugoandrade.euro2016.backend.object.Country;
import org.hugoandrade.euro2016.backend.object.Match;
import org.hugoandrade.euro2016.backend.object.SystemData;
import org.hugoandrade.euro2016.backend.utils.ISO8601;

/**
 * Parses the objects to Json data.
 */
public class MobileClientDataJsonFormatter {


    public JsonObject getAsJsonObject(Country country) {

        return JsonObjectBuilder.instance()
                .addProperty(Country.Entry.Cols._ID, country.getID())
                .addProperty(Country.Entry.Cols.NAME, country.getName())
                .addProperty(Country.Entry.Cols.MATCHES_PLAYED, country.getMatchesPlayed())
                .addProperty(Country.Entry.Cols.VICTORIES, country.getVictories())
                .addProperty(Country.Entry.Cols.DRAWS, country.getDraws())
                .addProperty(Country.Entry.Cols.DEFEATS, country.getDefeats())
                .addProperty(Country.Entry.Cols.GOALS_FOR, country.getGoalsFor())
                .addProperty(Country.Entry.Cols.GOALS_AGAINST, country.getGoalsAgainst())
                .addProperty(Country.Entry.Cols.GOALS_DIFFERENCE, country.getGoalsDifference())
                .addProperty(Country.Entry.Cols.GROUP, country.getGroup())
                .addProperty(Country.Entry.Cols.POSITION, country.getPosition())
                .addProperty(Country.Entry.Cols.POINTS, country.getPoints())
                .create();
    }

    public JsonObject getAsJsonObject(SystemData systemData) {

        return JsonObjectBuilder.instance()
                .addProperty(SystemData.Entry.Cols._ID, systemData.getID())
                .addProperty(SystemData.Entry.Cols.APP_STATE, systemData.getAppState())
                .addProperty(SystemData.Entry.Cols.RULES, systemData.getRawRules())
                .addProperty(SystemData.Entry.Cols.SYSTEM_DATE, ISO8601.fromCalendar(systemData.getSystemDate()))
                .addProperty(SystemData.Entry.Cols.DATE_OF_CHANGE, ISO8601.fromCalendar(systemData.getDateOfChange()))
                .create();
    }

    public JsonObject getAsJsonObject(Match match) {

        return JsonObjectBuilder.instance()
                .addProperty(Match.Entry.Cols._ID, match.getID())
                .addProperty(Match.Entry.Cols.MATCH_NO, match.getMatchNumber())
                .addProperty(Match.Entry.Cols.HOME_TEAM, match.getHomeTeam())
                .addProperty(Match.Entry.Cols.AWAY_TEAM, match.getAwayTeam())
                .addProperty(Match.Entry.Cols.HOME_TEAM_GOALS, match.getHomeTeamGoals())
                .addProperty(Match.Entry.Cols.AWAY_TEAM_GOALS, match.getAwayTeamGoals())
                .addProperty(Match.Entry.Cols.HOME_TEAM_NOTES, match.getHomeTeamNotes())
                .addProperty(Match.Entry.Cols.AWAY_TEAM_NOTES, match.getAwayTeamNotes())
                .addProperty(Match.Entry.Cols.GROUP, match.getGroup())
                .addProperty(Match.Entry.Cols.STAGE, match.getStage())
                .addProperty(Match.Entry.Cols.STADIUM, match.getStadium())
                .addProperty(Match.Entry.Cols.DATE_AND_TIME, ISO8601.fromDate(match.getDateAndTime()))
                .create();
    }

    private static class JsonObjectBuilder {

        private final JsonObject mJsonObject;

        private static JsonObjectBuilder instance() {
            return new JsonObjectBuilder();
        }

        private JsonObjectBuilder() {
            mJsonObject = new JsonObject();
        }

        JsonObjectBuilder addProperty(String property, String value) {
            mJsonObject.addProperty(property, value);
            return this;
        }

        JsonObjectBuilder addProperty(String property, Number value) {
            mJsonObject.addProperty(property, value);
            return this;
        }

        JsonObjectBuilder addProperty(String property, Boolean value) {
            mJsonObject.addProperty(property, value);
            return this;
        }

        JsonObject create() {
            return mJsonObject;
        }
    }
}
