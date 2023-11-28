import io.github.euristenede.GenerateInvoices;
import io.github.euristenede.dto.Input;
import io.github.euristenede.dto.Output;
import java.math.BigDecimal;
import java.sql.SQLException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author operador
 */
public class DesignPatternsTest {
    
    private GenerateInvoices generateInvoices;
    
    public DesignPatternsTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
        generateInvoices = new GenerateInvoices();
    }
    
    @AfterEach
    public void tearDown() {
    }

     @Test
     public void deveGerarAsNotasFiscaisPorRegimeDeCaixa() throws SQLException {
         Output[] output = generateInvoices.execute(new Input(12, 2022, "cash"));
         assertEquals("2022-01-05", output[0].getDate().toString());
         assertEquals(new BigDecimal(6000), output[0].getAmount());
     }
     
     @Test
     public void deveGerarAsNotasFiscaisPorRegimeDeCompetencia() throws SQLException {
         Output[] output = generateInvoices.execute(new Input(1, 2022, "accrual"));
         assertEquals("2022-01-01", output[0].getDate().toString());
         assertEquals(new BigDecimal(500), output[0].getAmount());
     }
}
