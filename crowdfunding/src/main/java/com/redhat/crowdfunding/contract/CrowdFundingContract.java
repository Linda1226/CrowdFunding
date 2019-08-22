package com.redhat.crowdfunding.contract;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import org.web3j.utils.Convert;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Bool;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.3.0.
 */
public class CrowdFundingContract extends Contract {
    private static final String BINARY = "0x608060405234801561001057600080fd5b506108f2806100206000396000f3fe608060405260043610610072576000357c01000000000000000000000000000000000000000000000000000000009004806311648a4d1461007457806317fba2121461009f5780633ece10421461017157806344f75550146102715780638259beda1461029f578063f2a5744114610332575b005b34801561008057600080fd5b50610089610381565b6040518082815260200191505060405180910390f35b3480156100ab57600080fd5b5061016f600480360360408110156100c257600080fd5b81019080803590602001906401000000008111156100df57600080fd5b8201836020820111156100f157600080fd5b8035906020019184600183028401116401000000008311171561011357600080fd5b91908080601f016020809104026020016040519081016040528093929190818152602001838380828437600081840152601f19601f8201169050808301925050505050505091929192908035906020019092919050505061038d565b005b34801561017d57600080fd5b506101aa6004803603602081101561019457600080fd5b81019080803590602001909291905050506104ac565b604051808673ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018060200185815260200184815260200183151515158152602001828103825286818151815260200191508051906020019080838360005b83811015610232578082015181840152602081019050610217565b50505050905090810190601f16801561025f5780820380516001836020036101000a031916815260200191505b50965050505050505060405180910390f35b61029d6004803603602081101561028757600080fd5b81019080803590602001909291905050506105cd565b005b3480156102ab57600080fd5b506102e2600480360360408110156102c257600080fd5b810190808035906020019092919080359060200190929190505050610782565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001838152602001828152602001935050505060405180910390f35b34801561033e57600080fd5b5061036b6004803603602081101561035557600080fd5b81019080803590602001909291905050506107f8565b6040518082815260200191505060405180910390f35b60008080549050905090565b600060c0604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020018481526020018381526020016000815260200160001515815260200160008152509080600181540180825580915050906001820390600052602060002090600702016000909192909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001019080519060200190610466929190610821565b50604082015181600201556060820151816003015560808201518160040160006101000a81548160ff02191690831515021790555060a082015181600501555050505050565b600060606000806000806000878154811015156104c557fe5b906000526020600020906007020190508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681600101826002015483600301548460040160009054906101000a900460ff16849450838054600181600116156101000203166002900480601f0160208091040260200160405190810160405280929190818152602001828054600181600116156101000203166002900480156105b25780601f10610587576101008083540402835291602001916105b2565b820191906000526020600020905b81548152906001019060200180831161059557829003601f168201915b50505050509350955095509550955095505091939590929450565b600080828154811015156105dd57fe5b906000526020600020906007020190508060040160009054906101000a900460ff1615151561060b57600080fd5b8060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff166108fc349081150290604051600060405180830381858888f19350505050158015610675573d6000803e3d6000fd5b503481600301600082825401925050819055506060604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020013481526020014281525081600601600083600501600081548092919060010191905055815260200190815260200160002060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015560408201518160020155905050670de0b6b3a764000081600201540281600301541015610760576000610763565b60015b8160040160006101000a81548160ff0219169083151502179055505050565b60008060008060008681548110151561079757fe5b9060005260206000209060070201600601600086815260200190815260200160002090508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1681600101548260020154935093509350509250925092565b6000808281548110151561080857fe5b9060005260206000209060070201600501549050919050565b828054600181600116156101000203166002900490600052602060002090601f016020900481019282601f1061086257805160ff1916838001178555610890565b82800160010185558215610890579182015b8281111561088f578251825591602001919060010190610874565b5b50905061089d91906108a1565b5090565b6108c391905b808211156108bf5760008160009055506001016108a7565b5090565b9056fea165627a7a7230582090cd3d9cf7da79d72aaaf7a1e10dcda90ae516f652ddcb9e8f6d5641121a4c6e0029";

    public static final String FUNC_GETFUNDCOUNT = "getFundCount";

    public static final String FUNC_RAISEFUND = "raiseFund";

    public static final String FUNC_GETFUNDINFO = "getFundInfo";

    public static final String FUNC_SENDCOIN = "sendCoin";

    public static final String FUNC_GETRECORDINFO = "getRecordInfo";

    public static final String FUNC_GETRECORDCOUNT = "getRecordCount";

    @Deprecated
    public CrowdFundingContract(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }



    @Deprecated
    public CrowdFundingContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public CrowdFundingContract(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteCall<BigInteger> getFundCount() {
        final Function function = new Function(FUNC_GETFUNDCOUNT,
                Arrays.<Type>asList(),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteCall<TransactionReceipt> raiseFund(String info, BigInteger goal) {
        final Function function = new Function(
                "raiseFund",
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(info),
                        new org.web3j.abi.datatypes.generated.Uint256(goal)),
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteCall<Tuple5<String, String, BigInteger, BigInteger, Boolean>> getFundInfo(BigInteger fundIndex) {
        final Function function = new Function(FUNC_GETFUNDINFO,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(fundIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}, new TypeReference<Bool>() {}));
        return new RemoteCall<Tuple5<String, String, BigInteger, BigInteger, Boolean>>(
                new Callable<Tuple5<String, String, BigInteger, BigInteger, Boolean>>() {
                    @Override
                    public Tuple5<String, String, BigInteger, BigInteger, Boolean> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple5<String, String, BigInteger, BigInteger, Boolean>(
                                (String) results.get(0).getValue(),
                                (String) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue(),
                                (BigInteger) results.get(3).getValue(),
                                (Boolean) results.get(4).getValue());
                    }
                });
    }

    public RemoteCall<TransactionReceipt> sendCoin(int fundIndex, int coin) {
        Function function = new Function("sendCoin", Arrays.<Type>asList(new Uint256(fundIndex)),
                Arrays.<TypeReference<?>>asList());
        return executeRemoteCallTransaction(function, BigInteger.valueOf(coin).multiply(Convert.Unit.ETHER.getWeiFactor().toBigInteger()));
    }

    public RemoteCall<Tuple3<String, BigInteger, BigInteger>> getRecordInfo(BigInteger fundIndex, BigInteger recordIndex) {
        final Function function = new Function(FUNC_GETRECORDINFO,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(fundIndex),
                        new org.web3j.abi.datatypes.generated.Uint256(recordIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Address>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteCall<Tuple3<String, BigInteger, BigInteger>>(
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(),
                                (BigInteger) results.get(1).getValue(),
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteCall<BigInteger> getRecordCount(BigInteger fundIndex) {
        final Function function = new Function(FUNC_GETRECORDCOUNT,
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(fundIndex)),
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    @Deprecated
    public static CrowdFundingContract load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrowdFundingContract(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static CrowdFundingContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new CrowdFundingContract(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }



    public static CrowdFundingContract load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new CrowdFundingContract(contractAddress, web3j, transactionManager, contractGasProvider);
    }



    @Deprecated
    public static RemoteCall<CrowdFundingContract> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CrowdFundingContract.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }



    @Deprecated
    public static RemoteCall<CrowdFundingContract> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(CrowdFundingContract.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }
}
