Pod::Spec.new do |s|
  s.name             = 'map4d_services_dtqg'
  s.version          = '0.1.0'
  s.summary          = 'Map4dServices for flutter.'
  s.description      = 'A flutter plugin for integrating Map4D Services in iOS and Android applications.'
  s.homepage         = 'https://map4d.vn'
  s.license          = { :file => '../LICENSE' }
  s.author           = { 'IOTLink' => 'admin@iotlink.com.vn' }
  s.source           = { :path => '.' }
  s.platform         = :ios, '12.0'

  # Flutter.framework does not contain a i386 slice.
  s.pod_target_xcconfig = { 'DEFINES_MODULE' => 'YES', 'EXCLUDED_ARCHS[sdk=iphonesimulator*]' => 'i386' }
  s.source_files        = 'Classes/**/*'
  s.public_header_files = 'Classes/**/*.h'

  s.dependency 'Flutter'
  s.dependency 'Map4dServicesDTQG', '~> 0.1'
end
