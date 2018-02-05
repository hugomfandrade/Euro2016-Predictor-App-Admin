package org.hugoandrade.euro2016.predictor.admin.cloudsim.parser;

import android.content.ContentValues;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import org.hugoandrade.euro2016.predictor.admin.object.Country;
import org.hugoandrade.euro2016.predictor.admin.object.Match;
import org.hugoandrade.euro2016.predictor.admin.object.User;
import org.hugoandrade.euro2016.predictor.admin.object.Prediction;
import org.hugoandrade.euro2016.predictor.admin.object.SystemData;
import org.hugoandrade.euro2016.predictor.admin.utils.ISO8601;

import java.util.Map;

/**
 * Parses the objects to ContentValues data.
 */
public class CloudContentValuesFormatter {

    public ContentValues getAsContentValues(Prediction prediction) {
        return ContentValuesBuilder.instance()
                .put(Prediction.Entry.Cols._ID, prediction.getID() == -1 ?
                        null : prediction.getID())
                .put(Prediction.Entry.Cols.MATCH_NO, prediction.getMatchNumber())
                .put(Prediction.Entry.Cols.HOME_TEAM_GOALS, prediction.getHomeTeamGoals() == -1 ?
                        null : prediction.getHomeTeamGoals())
                .put(Prediction.Entry.Cols.AWAY_TEAM_GOALS, prediction.getAwayTeamGoals() == -1 ?
                        null : prediction.getAwayTeamGoals())
                .put(Prediction.Entry.Cols.USER_ID, prediction.getUserID())
                .put(Prediction.Entry.Cols.SCORE, prediction.getScore() == -1 ?
                        null : prediction.getScore())
                .create();
    }

    public ContentValues getAsContentValues(User user) {
        return ContentValuesBuilder.instance()
                .put("_" + User.Entry.Cols.ID, user.getID())
                .put(User.Entry.Cols.EMAIL, user.getEmail())
                .put(User.Entry.Cols.PASSWORD, user.getPassword())
                .put(User.Entry.Cols.SCORE, user.getScore() == -1 ? null : user.getScore())
                .create();
    }

    public ContentValues getAsContentValues(JsonObject jsonObject) {
        ContentValuesBuilder builder = ContentValuesBuilder.instance();
        for (Map.Entry<String, JsonElement> entry: jsonObject.entrySet()) {
            // Because it is a boolean
            if (entry.getKey().equals(SystemData.Entry.Cols.APP_STATE)) {
                builder.put(entry.getKey(),
                        CloudDataJsonParser.getJsonPrimitive(jsonObject,
                                entry.getKey(),
                                false) ? 1 : 0);
            } else {
                String cname = entry.getKey();
                if (cname.equals("id"))
                    cname = "_id";

                builder.put(cname,
                        CloudDataJsonParser.getJsonPrimitive(jsonObject,
                                entry.getKey(),
                                null));
            }
        }
        return builder.create();

    }

    public ContentValues getAsContentValues(SystemData systemData) {
        return ContentValuesBuilder.instance()
                .put(SystemData.Entry.Cols.SYSTEM_DATE, ISO8601.fromCalendar(systemData.getSystemDate()))
                .put(SystemData.Entry.Cols.DATE_OF_CHANGE, ISO8601.fromCalendar(systemData.getDateOfChange()))
                .put(SystemData.Entry.Cols.RULES, systemData.getRawRules())
                .create();
    }

    public ContentValues getAsContentValues(Match match) {
        return ContentValuesBuilder.instance()
                .put(Match.Entry.Cols.MATCH_NUMBER, match.getMatchNumber())
                .put(Match.Entry.Cols.HOME_TEAM_ID,  match.getHomeTeamID())
                .put(Match.Entry.Cols.AWAY_TEAM_ID, match.getAwayTeamID())
                .put(Match.Entry.Cols.HOME_TEAM_GOALS, match.getHomeTeamGoals() == -1? null : String.valueOf(match.getHomeTeamGoals()))
                .put(Match.Entry.Cols.AWAY_TEAM_GOALS, match.getAwayTeamGoals() == -1? null : String.valueOf(match.getAwayTeamGoals()))
                .put(Match.Entry.Cols.HOME_TEAM_NOTES, match.getHomeTeamNotes())
                .put(Match.Entry.Cols.AWAY_TEAM_NOTES, match.getAwayTeamNotes())
                .put(Match.Entry.Cols.GROUP, match.getGroup())
                .put(Match.Entry.Cols.STAGE, match.getStage())
                .put(Match.Entry.Cols.STADIUM, match.getStadium())
                .put(Match.Entry.Cols.DATE_AND_TIME, ISO8601.fromDate(match.getDateAndTime()))
                .create();
    }

    public ContentValues getAsContentValues(Country country) {
        return ContentValuesBuilder.instance()
                .put(Country.Entry.Cols.NAME,  country.getName())
                .put(Country.Entry.Cols.MATCHES_PLAYED, country.getMatchesPlayed())
                .put(Country.Entry.Cols.VICTORIES, country.getVictories())
                .put(Country.Entry.Cols.DRAWS, country.getDraws())
                .put(Country.Entry.Cols.DEFEATS, country.getDefeats())
                .put(Country.Entry.Cols.GOALS_FOR, country.getGoalsFor())
                .put(Country.Entry.Cols.GOALS_AGAINST, country.getGoalsAgainst())
                .put(Country.Entry.Cols.GOALS_DIFFERENCE, country.getGoalsDifference())
                .put(Country.Entry.Cols.GROUP, country.getGroup())
                .put(Country.Entry.Cols.POSITION, country.getPosition())
                .put(Country.Entry.Cols.POINTS, country.getPoints())
                .put(Country.Entry.Cols.FAIR_PLAY_POINTS, country.getFairPlayPoints())
                .put(Country.Entry.Cols.COEFFICIENT, country.getCoefficient())
                .create();
    }

    private static class ContentValuesBuilder {

        private final ContentValues mContentValues;

        private static ContentValuesBuilder instance() {
            return new ContentValuesBuilder();
        }

        private ContentValuesBuilder() {
            mContentValues = new ContentValues();
        }

        ContentValuesBuilder put(String key, String value) {
            mContentValues.put(key, value);
            return this;
        }

        ContentValuesBuilder put(String key, Integer value) {
            mContentValues.put(key, value);
            return this;
        }

        ContentValuesBuilder put(String key, float value) {
            mContentValues.put(key, value);
            return this;
        }

        ContentValuesBuilder put(String key, Boolean value) {
            mContentValues.put(key, value);
            return this;
        }

        ContentValues create() {
            return mContentValues;
        }
    }
}
