import '../results.dart';

abstract class MFPlaceDetailResult {
  String get id;
  String get name;
  String get address;
  MFLocationComponent get location;
  List<String> get types;

  String? get description;
  List<String>? get tags;
  List<MFPlaceMetadataResult>? get metadatas;
  List<MFPlacePhotoResult>? get photos;
  List<MFPlaceAddressComponentResult>? get addressComponents;
  String? get objectId;
}

class MFPlaceDetailResultImpl implements MFPlaceDetailResult {
  final String _id;
  final String _name;
  final String _address;
  final MFLocationComponent _location;
  final List<String> _types;


  final String? _description;
  final List<String>? _tags;
  final List<MFPlaceMetadataResult>? _metadatas;
  final List<MFPlacePhotoResult>? _photos;
  final List<MFPlaceAddressComponentResult>? _addressComponents;
  final String? _objectId;

  MFPlaceDetailResultImpl._(
    this._id,
    this._name,
    this._address,
    this._location,
    this._types,
    this._description,
    this._tags,
    this._metadatas,
    this._photos,
    this._addressComponents,
    this._objectId,
  );

  static MFPlaceDetailResultImpl? fromMap(Object? json) {
    if (json == null || json is! Map<dynamic, dynamic>) {
      return null;
    }

    final metadatas = <MFPlaceMetadataResult>[];
    final metadatasJson = json['metadata'] as List<dynamic>;
    for (final metadataJson in metadatasJson) {
      final metadata = MFPlaceMetadataResultImpl.fromMap(metadataJson);
      if (metadata != null) {
        metadatas.add(metadata);
      }
    }

    final photos = <MFPlacePhotoResult>[];
    final photosJson = json['photos'] as List<dynamic>;
    for (final photoJson in photosJson) {
      final photo = MFPlacePhotoResultImpl.fromMap(photoJson);
      if (photo != null) {
        photos.add(photo);
      }
    }

    final addressComponents = <MFPlaceAddressComponentResult>[];
    final addressComponentsJson = json['addressComponents'] as List<dynamic>;
    for (final addressComponentJson in addressComponentsJson) {
      final addressComponent = MFPlaceAddressComponentResultImp.fromMap(addressComponentJson);
      if (addressComponent != null) {
        addressComponents.add(addressComponent);
      }
    }
    
    return MFPlaceDetailResultImpl._(
      json['id'],
      json['name'],
      json['address'],
      MFLocationComponent.fromJson(json['location'])!,
      (json['types'] as List<dynamic>).cast<String>(),
      json['description'],
      (json['tags'] as List<dynamic>).cast<String>(),
      metadatas.isNotEmpty ? metadatas : null,
      photos.isNotEmpty ? photos : null,
      addressComponents.isNotEmpty ? addressComponents : null,
      json['objectId'],
    );
  }

  @override
  String get address => _address;

  @override
  String get id => _id;

  @override
  MFLocationComponent get location => _location;

  @override
  String get name => _name;

  @override
  List<String> get types => _types;

  @override
  String? get description => _description;

  @override
  List<String>? get tags => _tags;

  @override
  List<MFPlaceMetadataResult>? get metadatas => _metadatas;

  @override
  List<MFPlacePhotoResult>? get photos => _photos;

  @override
  List<MFPlaceAddressComponentResult>? get addressComponents => _addressComponents;

  @override
  String? get objectId => _objectId;
}