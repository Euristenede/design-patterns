package io.github.euristenede;

import io.github.euristenede.dto.Input;
import io.github.euristenede.dto.Output;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 *
 * @author Euristenede Santos
 */
public class GenerateInvoices {

    public Output[] execute(Input input) throws SQLException {
        Connection connection = null;
        String url = "jdbc:postgresql://localhost:5432/postgres";
        String user = "postgres";
        String password = "postgres";

        Output[] output = new Output[12];

        // Tenta estabelecer a conexão
        try {
            connection = DriverManager.getConnection(url, user, password);
            if (connection != null) {

                try (PreparedStatement stContract = connection.prepareStatement("SELECT * FROM designpatterns.contract"); ResultSet rsContract = stContract.executeQuery()) {

                    while (rsContract.next()) {
                        if (input.getType().equals("cash")) {
                            try (PreparedStatement stContrato
                                    = connection.prepareStatement("SELECT * FROM designpatterns.payment where id_contract = '" + rsContract.getString("id_contract") + "'"); ResultSet rsPayment = stContrato.executeQuery()) {

                                int indexPayment = 0;

                                while (rsPayment.next()) {

                                    if (rsPayment.getDate("date").getMonth() + 1 != input.getMonth()
                                            || rsPayment.getDate("date").getYear() != input.getYear()) {
                                        output[indexPayment] = new Output(rsPayment.getBigDecimal("amount"),
                                                rsPayment.getDate("date").toLocalDate());
                                    }

                                    indexPayment++;
                                }
                                return output;
                            } catch (SQLException e) {
                                System.err.println("Erro ao executar a consulta: " + e.getMessage());
                            }
                        }
                        if (input.getType().equals("accrual")) {
                            int period = 0;
                            while (period < rsContract.getInt("periods")) {
                                LocalDate date = rsContract.getDate("date").toLocalDate().plusMonths(period++);
                                BigDecimal amount = rsContract.getBigDecimal("amount").divide(new BigDecimal(rsContract.getInt("periods")));
                                if (date.getMonthValue() != input.getMonth() || date.getYear() != input.getYear()) {
                                    continue;
                                }
                                output[0] = new Output(amount, date);
                            }
                        }
                    }
                    return output;
                } catch (SQLException e) {
                    System.err.println("Erro ao executar a consulta: " + e.getMessage());
                }
            } else {
                return null;
            }
        } catch (SQLException e) {
            return null;
        } finally {
            connection.close();
        }
        return null;
    }
}
