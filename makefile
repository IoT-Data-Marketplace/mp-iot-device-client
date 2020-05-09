build-and-push-to-ecr:
	docker build -t mp-iot-device-client .
	docker tag mp-iot-device-client:latest ddanijeld/mp-iot-device-client:latest
	docker push ddanijeld/mp-iot-device-client:latest