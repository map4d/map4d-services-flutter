import 'results.dart';
import 'service_error.dart';

///
///
bool validateResponse(Map<String, dynamic>? response) {
  if (response == null) {
    throw MFServiceError('empty', 'Response is null');
  }

  if (response['code'] != 'ok') {
    throw MFServiceError(response['code'], response['message']);
  }

  return true;
}

///
///
List<MFPlaceResult> toListPlace(Object? result) {
  if (result == null || result is! List<dynamic>) {
    return [];
  }

  final places = <MFPlaceResult>[];
  for (final e in result) {
    final place = PlaceResult.fromMap(e);
    if (place != null) {
      places.add(place);
    }
  }

  return places;
}

///
///
List<MFSuggestionResult> toListSuggestion(Object? result) {
  if (result == null || result is! List<dynamic>) {
    return [];
  }

  final suggestions = <MFSuggestionResult>[];
  for (final e in result) {
    final suggestion = SuggestionResult.fromMap(e);
    if (suggestion != null) {
      suggestions.add(suggestion);
    }
  }

  return suggestions;
}

///
///
List<MFGeocodeResult> toListGeocode(Object? result) {
  if (result == null || result is! List<dynamic>) {
    return [];
  }

  final codes = <MFGeocodeResult>[];
  for (final e in result) {
    final code = GeocodeResult.fromMap(e);
    if (code != null) {
      codes.add(code);
    }
  }

  return codes;
}
