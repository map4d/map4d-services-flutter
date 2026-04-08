# Map4dServices for Flutter
[![map4d](https://img.shields.io/badge/map4d-services-orange)](https://map4d.vn/)
[![platform](https://img.shields.io/badge/platform-flutter-45d2fd.svg)](https://flutter.dev/)
[![pub package](https://img.shields.io/pub/v/map4d_services.svg)](https://pub.dev/packages/map4d_services)

A flutter plugin for integrating [Map4D Services](https://api.map4d.vn/sdk/docs/index.html) in iOS and Android applications.

## Requirements
- Android SDK 21+
- iOS 12.0+

## Installing

To use this plugin, add `map4d_services` as a dependency in your `pubspec.yaml` file.

```shell
flutter pub add map4d_services
```

## Setup Services API key

The Services API key is a unique identifier that authenticates requests associated with your project for usage and billing purposes. You must have at least one API key associated with your project.

Get an API key at <https://map.map4d.vn/user/>

### Android

Provide services api key from `android/app/src/main/AndroidManifest.xml`

```xml
<manifest xmlns:android="http://schemas.android.com/apk/res/android">
    <application>
        <meta-data
            android:name="vn.map4d.services.ACCESS_KEY"
            android:value="YOUR_SERVICES_ACCESS_KEY"/>
    </application>
</manifest>
```

### iOS

Provide services api key from `ios/Runner/Info.plist`

```xml
<key>Map4dServicesAccessKey</key>
<string>YOUR_SERVICES_ACCESS_KEY</string>
```

## Simple Usage

```dart
import 'package:flutter/material.dart';
import 'package:map4d_services/map4d_services.dart';

void getPlaceDetail() async {
  MFServices.places
      .fetchPlaceDetail('5c88df71d2c05acd14848f9e')
      .then((detail) => {print('Place Detail: $detail')})
      .onError<MFServiceError>((error, stackTrace) =>
          {print('Place Detail Error: ${error.code}, ${error.message}')});
}
```

See [example](https://github.com/map4d/map4d-services-flutter/tree/master/example) directory for more examples

## Documents

- Guides: <https://docs.map4d.vn/map4d-service/flutter/>
- API Reference: <https://pub.dev/documentation/map4d_services/latest/>