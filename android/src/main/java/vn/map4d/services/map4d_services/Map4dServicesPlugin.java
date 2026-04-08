package vn.map4d.services.map4d_services;

import androidx.annotation.NonNull;

import io.flutter.embedding.engine.plugins.FlutterPlugin;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import vn.map4d.services.MFServicesOptions;

/** Map4dServicesPlugin */
public class Map4dServicesPlugin implements FlutterPlugin, MethodCallHandler {
  /// The MethodChannel that will the communication between Flutter and native Android
  ///
  /// This local reference serves to register the plugin with the Flutter Engine and unregister it
  /// when the Flutter Engine is detached from the Activity
  private MethodChannel channel;

  private MFServicesOptions servicesOptions;

  @Override
  public void onAttachedToEngine(@NonNull FlutterPluginBinding flutterPluginBinding) {
    channel = new MethodChannel(flutterPluginBinding.getBinaryMessenger(), "map4d_services");
    channel.setMethodCallHandler(this);
    servicesOptions = new MFServicesOptions.Builder(flutterPluginBinding.getApplicationContext()).build();
  }

  @Override
  public void onMethodCall(@NonNull MethodCall call, @NonNull Result result) {
    switch (call.method) {
      case "geocode#geocode": {
        SClient.fireRequest(servicesOptions, SRequest.buildGeocodingRequestWithData(call), result);
        break;
      }
      case "place#detail": {
        SClient.fireRequest(servicesOptions, SRequest.buildPlaceDetailRequestWithId(call), result);
        break;
      }
      case "place#text-search": {
        SClient.fireRequest(servicesOptions, SRequest.buildTextSearchRequestWithData(call), result);
        break;
      }
      case "place#nearby-search": {
        SClient.fireRequest(servicesOptions, SRequest.buildNearbySearchRequestWithData(call), result);
        break;
      }
      case "place#viewbox-search": {
        SClient.fireRequest(servicesOptions, SRequest.buildViewboxSearchRequestWithData(call), result);
        break;
      }
      case "place#autosuggest": {
        SClient.fireRequest(servicesOptions, SRequest.buildSuggestionsRequestWithData(call), result);
        break;
      }
      case "route#route": {
        SClient.fireRequest(servicesOptions, SRequest.buildDirectionsRequestWithData(call), result);
        break;
      }
      case "route#matrix": {
        SClient.fireRequest(servicesOptions, SRequest.buildDistanceMatrixRequestWithData(call), result);
        break;
      }
      default:
        result.notImplemented();
        break;
    }
  }

  @Override
  public void onDetachedFromEngine(@NonNull FlutterPluginBinding binding) {
    channel.setMethodCallHandler(null);
  }
}
