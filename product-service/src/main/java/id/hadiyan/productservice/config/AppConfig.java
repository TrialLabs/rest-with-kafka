package id.hadiyan.productservice.config;

import id.hadiyan.commonservice.config.CommonConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({CommonConfig.class})
public class AppConfig {
}
