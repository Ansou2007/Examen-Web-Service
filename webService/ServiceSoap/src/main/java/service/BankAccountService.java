package service;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import java.util.List;


@WebService
public interface BankAccountService {
    @WebMethod
    double getBalance(@WebParam(name = "accountId") String accountId);

    @WebMethod
    List<Transaction> getTransactions(@WebParam(name = "accountId") String accountId,
                                      @WebParam(name = "pageNumber") int pageNumber,
                                      @WebParam(name = "pageSize") int pageSize);

    @WebMethod
    boolean transferFunds(@WebParam(name = "creditor") String creditor,
                          @WebParam(name = "debtor") String debtor,
                          @WebParam(name = "amount") double amount,
                          @WebParam(name = "currency") String currency);
}
