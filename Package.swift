// swift-tools-version: 5.9
import PackageDescription

let package = Package(
    name: "PrintPlugin",
    platforms: [.iOS(.v13)],
    products: [
        .library(
            name: "PrintPlugin",
            targets: ["printPluginPlugin"])
    ],
    dependencies: [
        .package(url: "https://github.com/ionic-team/capacitor-swift-pm.git", branch: "main")
    ],
    targets: [
        .target(
            name: "printPluginPlugin",
            dependencies: [
                .product(name: "Capacitor", package: "capacitor-swift-pm"),
                .product(name: "Cordova", package: "capacitor-swift-pm")
            ],
            path: "ios/Sources/printPluginPlugin"),
        .testTarget(
            name: "printPluginPluginTests",
            dependencies: ["printPluginPlugin"],
            path: "ios/Tests/printPluginPluginTests")
    ]
)