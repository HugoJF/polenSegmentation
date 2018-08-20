# [EXPERIMENTAL] Pollen segmentator

## Current status: DEPRECATED 

Small experimental GUI software developed to test polygon fitting technique with thresholding to extract single pollen pictures from a single microscopic image. It heavily relies on BoofCV libraries to work and more information about thresholding in BoofCV can be found [here](https://boofcv.org/index.php?title=Example_Thresholding) and is also extended from a [previous attempt](https://github.com/HugoJF/polenDetector) at pollen segmentation using pure binary thresholding.

This software exposes 5 parameters in a user-friendly UI with automatic updates to the preview window, marking each detection with a precise contour (in red) and a bounding box (in green), each can be used further to improve detection.

The main difference from PolenDetector is: filtering the binary thresholded image with an attempt to fit a polygon around the pollen with restraints on minimum vertex distance and angle (in an attempt to avoid selecting stuck pollen grains like shown below)

<p align="center">
<img src="https://i.imgur.com/1PxSLit.png" height=300>
</p>

## Used in this project
- Java 1.7
- BoofCV library
- Maven

## Usage
Execute with `java -jar polen_segmentation.jar`, no parameters needed.

## Future
I have no plans on updating this project (there are multiple things that could be improved) since it started just as a proof of concept on thresholding as a segmentation technique in pollen images that showed to be still imprecise alone without any feature checking.

![Sample image of the software running and results shown ](https://i.imgur.com/KzDA4mg.png)
