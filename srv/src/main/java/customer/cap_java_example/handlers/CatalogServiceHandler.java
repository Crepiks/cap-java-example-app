package customer.cap_java_example.handlers;

import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import com.sap.cds.services.EventContext;
import com.sap.cds.services.cds.CdsService;
import com.sap.cds.services.handler.EventHandler;
import com.sap.cds.services.handler.annotations.After;
import com.sap.cds.services.handler.annotations.Before;
import com.sap.cds.services.handler.annotations.ServiceName;

import cds.gen.catalogservice.CatalogService_;
import cds.gen.catalogservice.Books;

@Component
@ServiceName(CatalogService_.CDS_NAME)
public class CatalogServiceHandler implements EventHandler {

	@Before(event = CdsService.EVENT_READ)
	public void beforeRead(EventContext context) {
		System.out.println(context.getUserInfo().isSystemUser());
		System.out.println("Before read is executed");
	}

	@After(event = CdsService.EVENT_READ)
	public void discountBooks(Stream<Books> books) {
		System.out.println("After is executed");
		System.out.println(books.count() + " books found");

		// books.filter(b -> b.getTitle() != null && b.getStock() != null)
		// .filter(b -> b.getStock() > 200)
		// .forEach(b -> b.setTitle(b.getTitle() + " (discounted)"));
	}

}