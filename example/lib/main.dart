import 'dart:developer';

import 'package:flutter/material.dart';
import 'package:map4d_services/map4d_services.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatefulWidget {
  const MyApp({Key? key}) : super(key: key);

  @override
  State<MyApp> createState() => _MyAppState();
}

class _MyAppState extends State<MyApp> {
  void _placeDetail() async {
    MFServices.places
        .fetchPlaceDetail('5c88df71d2c05acd14848f9e')
        .then((detail) => {
              print('Place Detail: $detail'),
              inspect(detail),
            })
        .onError<MFServiceError>((error, stackTrace) =>
            {print('Place Detail Error: ${error.code}, ${error.message}')});
  }

  void _autoSuggest() async {
    try {
      final places = await MFServices.places.fetchSuggestion(
        'Tam Giang',
        location: const MFLocationComponent(
            latitude: 16.575619, longitude: 107.530756),
        acronym: false,
      );
      print('Auto Suggest: $places');
      inspect(places);
    } on MFServiceError catch (error) {
      print('Auto Suggest Error: ${error.code}, ${error.message}');
    }
  }

  void _textSearch() async {
    final places = await MFServices.places.fetchTextSearch('abc',
        types: ['atm'],
        datetime: DateTime.now(),
        location: const MFLocationComponent(
            latitude: 16.075177, longitude: 108.220228));
    //textSearch['result'][0]['name'];
    print('Text Search: $places');
    inspect(places);
  }

  void _nearbySearch() async {
    try {
      final places = await MFServices.places.fetchNearbySearch(
        const MFLocationComponent(latitude: 16.075177, longitude: 108.220228),
        20000,
        text: 'Sịa',
        // tags: ['point'],
        types: ['point'],
        datetime: DateTime.now(),
      );
      print('Nearby Search: $places');
      inspect(places);
    } on MFServiceError catch (error) {
      print('Nearby Search Error: ${error.code}, ${error.message}');
    }
  }

  void _viewboxSearch() async {
    try {
      final places = await MFServices.places.fetchViewboxSearch(
        const MFViewboxComponent(
            southwest: MFLocationComponent(
                latitude: 16.056453967981348, longitude: 108.19387435913086),
            northeast: MFLocationComponent(
                latitude: 16.093031550262133, longitude: 108.25927734375)),
        text: 'a',
        // tags: ['Coopmart'],
        types: ['atm'],
        datetime: DateTime.now(),
      );
      print('Viewbox Search: $places');
      inspect(places);
    } on MFServiceError catch (error) {
      print('Viewbox Search Error: ${error.code}, ${error.message}');
    }
  }

  void _geocode() async {
    try {
      final geos = await MFServices.places.fetchGeocode(
          location: const MFLocationComponent(
              latitude: 16.024634, longitude: 108.209217),
          address: '31 Lê Văn Duyệt',
          viewbox: const MFViewboxComponent(
              southwest: MFLocationComponent(
                  latitude: 16.056453967981348, longitude: 108.19387435913086),
              northeast: MFLocationComponent(
                  latitude: 16.093031550262133, longitude: 108.25927734375)));
      print('Geocode: $geos');
      inspect(geos);
    } on MFServiceError catch (error) {
      print('Geocode Error: ${error.code}, ${error.message}');
    }
  }

  void _directions() async {
    List<MFRouteType> routeTypes = <MFRouteType>[
      MFRouteType.motorway,
      MFRouteType.tunnel
    ];
    List<MFLocationComponent> waypoints = <MFLocationComponent>[
      const MFLocationComponent(
          latitude: 16.081126855919138, longitude: 108.21412717916483)
    ];
    MFRouteRestriction restriction = MFRouteRestriction.restrictCircleArea(
        const MFLocationComponent(latitude: 16.080611, longitude: 108.218113),
        30,
        types: routeTypes);
    try {
      final directions = await MFServices.routes.fetchDirections(
          const MFLocationComponent(
              latitude: 16.08116088350121, longitude: 108.21979357460582),
          const MFLocationComponent(
              latitude: 16.08334260545329, longitude: 108.21651589082553),
          waypoints: waypoints,
          restriction: restriction);
      print('Directions: $directions');
      inspect(directions);
    } on MFServiceError catch (error) {
      print('Directions Error: ${error.code}, ${error.message}');
    }
  }

  void _routeMatrix() async {
    List<MFRouteType> routeTypes = <MFRouteType>[
      MFRouteType.motorway,
      MFRouteType.tunnel
    ];
    List<MFLocationComponent> origins = <MFLocationComponent>[
      const MFLocationComponent(latitude: 16.024634, longitude: 108.209217),
      const MFLocationComponent(latitude: 16.0717664, longitude: 108.2236151)
    ];

    List<MFLocationComponent> destinations = <MFLocationComponent>[
      const MFLocationComponent(latitude: 16.0717664, longitude: 108.2236151),
      const MFLocationComponent(latitude: 16.06104, longitude: 108.2167)
    ];

    MFRouteRestriction restriction = MFRouteRestriction.restrictCircleArea(
        const MFLocationComponent(latitude: 16.080611, longitude: 108.218113),
        30,
        types: routeTypes);

    MFServices.routes
        .fetchDistanceMatrix(origins, destinations, restriction: restriction)
        .then((matrix) => {print('Matrix: $matrix'), inspect(matrix)})
        .onError<MFServiceError>((error, stackTrace) =>
            {print('Matrix Error: ${error.code}, ${error.message}')});
  }

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Scaffold(
        appBar: AppBar(
          title: const Text('Map4D Services'),
        ),
        body: ListView(
          children: [
            ListTile(
              leading: const Icon(Icons.place),
              title: const Text('Place Detail'),
              onTap: _placeDetail,
            ),
            ListTile(
              leading: const Icon(Icons.place),
              title: const Text('Auto Suggest'),
              onTap: _autoSuggest,
            ),
            ListTile(
              leading: const Icon(Icons.place),
              title: const Text('Text Search'),
              onTap: _textSearch,
            ),
            ListTile(
              leading: const Icon(Icons.place),
              title: const Text('Nearby Search'),
              onTap: _nearbySearch,
            ),
            ListTile(
              leading: const Icon(Icons.place),
              title: const Text('Viewbox Search'),
              onTap: _viewboxSearch,
            ),
            ListTile(
              leading: const Icon(Icons.adjust),
              title: const Text('Geocode'),
              onTap: _geocode,
            ),
            ListTile(
              leading: const Icon(Icons.edit_road),
              title: const Text('Directions'),
              onTap: _directions,
            ),
            ListTile(
              leading: const Icon(Icons.edit_road),
              title: const Text('Route Matrix'),
              onTap: _routeMatrix,
            ),
          ],
        ),
      ),
    );
  }
}
