package barqsoft.footballscores.rest.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by a.g.seliverstov on 24.12.2015.
 */
public class Team {
    @SerializedName("_links")
    @Expose
    private Links links;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("shortName")
    @Expose
    private String shortName;
    @SerializedName("squadMarketValue")
    @Expose
    private String squadMarketValue;
    @SerializedName("crestUrl")
    @Expose
    private String crestUrl;
    @SerializedName("code")
    @Expose
    private String code;

    public Links getLinks(){
        return links;
    }

    public void setLinks(Links links){
        this.links=links;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getSquadMarketValue() {
        return squadMarketValue;
    }

    public void setSquadMarketValue(String squadMarketValue) {
        this.squadMarketValue = squadMarketValue;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public void setCrestUrl(String crestUrl) {
        this.crestUrl = crestUrl;
    }

    public String getCodel() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
