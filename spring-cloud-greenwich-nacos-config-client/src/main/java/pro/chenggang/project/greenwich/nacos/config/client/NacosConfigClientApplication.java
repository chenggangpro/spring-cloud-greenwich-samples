package pro.chenggang.project.greenwich.nacos.config.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class NacosConfigClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(NacosConfigClientApplication.class, args);
	}
}
