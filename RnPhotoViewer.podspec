require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "RnPhotoViewer"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => min_ios_version_supported }
  s.source       = { :git => "https://github.com/472647301/react-native-photo-viewer.git", :tag => "#{s.version}" }

  s.resource = 'ios/Resources/*.{bundle}'
  s.resource_bundles = { 'GKPhotoBrowser.Privacy' => 'ios/Resources/PrivacyInfo.xcprivacy' }
  s.source_files = "ios/**/*.{h,m,mm,cpp}"
  s.private_header_files = "ios/**/*.h"
  s.dependency "SDWebImage"
  s.dependency "AFNetworking"
  s.dependency "GKLivePhotoManager"
  s.dependency "GKSliderView"
  s.dependency "Toast"

 install_modules_dependencies(s)
end
