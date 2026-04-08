package vn.map4d.services.map4d_services;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.flutter.plugin.common.MethodCall;
import vn.map4d.services.MFMethod;
import vn.map4d.services.MFRequestParams;
import vn.map4d.services.MFServiceOptions;

class SRequest {
  static MFServiceOptions buildGeocodingRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/v2/geocode");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();
    String address = call.argument("address");
    if (address != null) {
      params.put("address", address);
    }

    String location = SConverter.toLocation(call.argument("location"));
    if (location != null) {
      params.put("location", location);
    }

    String viewbox = SConverter.toViewbox(call.argument("viewbox"));
    if (viewbox != null) {
      params.put("viewbox", viewbox);
    }
    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildPlaceDetailRequestWithId(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    StringBuilder urlBuilder = new StringBuilder("/sdk/place/detail");
    serviceOptionsBuilder.method(MFMethod.GET);
    String id = call.argument("id");
    if (id != null) {
      urlBuilder.append("/").append(id);
    }
    serviceOptionsBuilder.url(urlBuilder.toString());
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildTextSearchRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/place/text-search");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();
    String text = call.argument("text");
    if (text != null) {
      params.put("text", text);
    }

    String location = SConverter.toLocation(call.argument("location"));
    if (location != null) {
      params.put("location", location);
    }

    String types = SConverter.toTypes(call.argument("types"));
    if (types != null) {
      params.put("types", types);
    }

    String datetime = SConverter.toDatetime(call.argument("datetime"));
    if (datetime != null) {
      params.put("datetime", datetime);
    }
    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildNearbySearchRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/place/nearby-search");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();
    String location = SConverter.toLocation(call.argument("location"));
    if (location != null) {
      params.put("location", location);
    }

    String radius = SConverter.toRadius(call.argument("radius"));
    if (radius != null) {
      params.put("radius", radius);
    }

    String text = call.argument("text");
    if (text != null) {
      params.put("text", text);
    }

    String types = SConverter.toTypes(call.argument("types"));
    if (types != null) {
      params.put("types", types);
    }

    String tags = SConverter.toTags(call.argument("tags"));
    if (tags != null) {
      params.put("tags", tags);
    }

    String datetime = SConverter.toDatetime(call.argument("datetime"));
    if (datetime != null) {
      params.put("datetime", datetime);
    }
    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildViewboxSearchRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/place/viewbox-search");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();
    String viewbox = SConverter.toViewbox(call.argument("viewbox"));
    if (viewbox != null) {
      params.put("viewbox", viewbox);
    }

    String text = call.argument("text");
    if (text != null) {
      params.put("text", text);
    }

    String types = SConverter.toTypes(call.argument("types"));
    if (types != null) {
      params.put("types", types);
    }

    String tags = SConverter.toTags(call.argument("tags"));
    if (tags != null) {
      params.put("tags", tags);
    }

    String datetime = SConverter.toDatetime(call.argument("datetime"));
    if (datetime != null) {
      params.put("datetime", datetime);
    }
    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildSuggestionsRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/autosuggest");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();
    String text = call.argument("text");
    if (text != null) {
      params.put("text", text);
    }

    String acronym = SConverter.toAcronym(call.argument("acronym"));
    if (acronym != null) {
      params.put("acronym", acronym);
    }

    String location = SConverter.toLocation(call.argument("location"));
    if (location != null) {
      params.put("location", location);
    }
    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildDirectionsRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/route");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();

    String origin = SConverter.toLocation(call.argument("origin"));
    if (origin != null) {
      params.put("origin", origin);
    }

    String destination = SConverter.toLocation(call.argument("destination"));
    if (destination != null) {
      params.put("destination", destination);
    }

    String mode = SConverter.toMode(call.argument("mode"));
    if (mode != null) {
      params.put("mode", mode);
    }

    String weighting = SConverter.toWeighting(call.argument("weighting"));
    if (weighting != null) {
      params.put("weighting", weighting);
    }

    String language = SConverter.toLanguage(call.argument("language"));
    if (language != null) {
      params.put("language", language);
    }

    String points = SConverter.toLocationList(call.argument("points"));
    if (points != null) {
      params.put("points", points);
    }

    Object avoidObject = call.argument("restriction");
    String avoid = SConverter.toAvoid(avoidObject);
    if (avoid != null) {
      params.put("avoid", avoid);
    }

    String avoidRoads = SConverter.toAvoidRoads(avoidObject);
    if (avoidRoads != null) {
      params.put("avoidRoads", avoidRoads);
    }

    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

  static MFServiceOptions buildDistanceMatrixRequestWithData(@NonNull MethodCall call) {
    MFServiceOptions.Builder serviceOptionsBuilder = new MFServiceOptions.Builder();
    serviceOptionsBuilder.url("/sdk/route/matrix");
    serviceOptionsBuilder.method(MFMethod.GET);

    MFRequestParams params = new MFRequestParams();

    String origins = SConverter.toLocationList(call.argument("origins"));
    if (origins != null) {
      params.put("origins", origins);
    }

    String destinations = SConverter.toLocationList(call.argument("destinations"));
    if (destinations != null) {
      params.put("destinations", destinations);
    }

    String mode = SConverter.toMode(call.argument("mode"));
    if (mode != null) {
      params.put("mode", mode);
    }

    String weighting = SConverter.toWeighting(call.argument("weighting"));
    if (weighting != null) {
      params.put("weighting", weighting);
    }

    String language = SConverter.toLanguage(call.argument("language"));
    if (language != null) {
      params.put("language", language);
    }

    Object avoidObject = call.argument("restriction");
    String avoid = SConverter.toAvoid(avoidObject);
    if (avoid != null) {
      params.put("avoid", avoid);
    }

    String avoidRoads = SConverter.toAvoidRoads(avoidObject);
    if (avoidRoads != null) {
      params.put("avoidRoads", avoidRoads);
    }

    serviceOptionsBuilder.params(params);
    return serviceOptionsBuilder.build();
  }

}
