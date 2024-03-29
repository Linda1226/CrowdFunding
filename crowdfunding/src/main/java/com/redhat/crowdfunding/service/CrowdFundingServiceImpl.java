package com.redhat.crowdfunding.service;

import com.redhat.crowdfunding.contract.CrowdFundingContract;
import com.redhat.crowdfunding.model.Fund;
import com.redhat.crowdfunding.model.Record;
import com.redhat.crowdfunding.util.Consts;
import com.redhat.crowdfunding.util.Utils;
import org.web3j.abi.datatypes.Type;
import org.web3j.crypto.CipherException;
import org.web3j.crypto.Credentials;
import org.web3j.crypto.WalletUtils;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tuples.generated.Tuple5;
import org.web3j.utils.Convert;

import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author niyuelin1990
 */
public class CrowdFundingServiceImpl implements CrowdFundingService {

    private CrowdFundingContract contract;

    public CrowdFundingServiceImpl() throws IOException, CipherException {
        // 获取凭证
        Credentials credentials = WalletUtils.loadCredentials(Consts.PASSWORD, Consts.PATH);
        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService(Consts.URL));
        contract = CrowdFundingContract.load(Consts.ADDRESS, web3j, credentials, Consts.GAS_PRICE, Consts.GAS_LIMIT);
    }

    public CrowdFundingServiceImpl(String password, String content) throws IOException, CipherException {
        // 获取凭证
        File tmp = Utils.StoreFile(content); // 从客户端上传的钱包
        Credentials credentials = WalletUtils.loadCredentials(password, tmp);
        // defaults to http://localhost:8545/
        Web3j web3j = Web3j.build(new HttpService(Consts.URL));
        contract = CrowdFundingContract.load(Consts.ADDRESS, web3j, credentials, Consts.GAS_PRICE, Consts.GAS_LIMIT);
    }

    /**
     * 获取众筹项目数量
     *
     * @return 项目数量
     * @throws Exception
     */
    public int getFundCount() throws Exception {
        return contract.getFundCount().send().intValue();
    }

    /**
     * 获取众筹项目信息
     *
     * @param pageIndex 页面索引
     * @param pageSize  页面大小
     * @return list(众筹地址 众筹描述 众筹目标 已筹金币 是否结束 参与人数)
     * @throws Exception
     */
    public List<Fund> getFunds(int pageIndex, int pageSize) throws Exception {
        List<Fund> fList = new ArrayList<Fund>();
        // getFundCount
        int count = contract.getFundCount().send().intValue();
        int from = pageIndex * pageSize;
        int to = Math.min((pageIndex + 1) * pageSize, count);
        for (int fundIndex = from; fundIndex < to; fundIndex++) {
            // getFundInfo
            Tuple5<String, String, BigInteger, BigInteger, Boolean> finfo = contract.getFundInfo(BigInteger.valueOf(fundIndex)).send();

            Fund fund = new Fund();
            fund.setFundIndex(fundIndex);
            fund.setOwner(finfo.getValue1().toString());
            fund.setDesc(finfo.getValue2().toString());
            fund.setGoal(Integer.parseInt(finfo.getValue3().toString()));
            fund.setCoins(new BigInteger(finfo.getValue4().toString()).divide(Convert.Unit.ETHER.getWeiFactor().toBigInteger()).intValue());
            fund.setFinished(Boolean.parseBoolean(finfo.getValue5().toString()));
            fList.add(fund);
        }
        Collections.reverse(fList); // 按照项目编号降序排序
        return fList;
    }

    /**
     * 获取众筹捐赠记录
     *
     * @param fundIndex 项目编号
     * @return list(捐赠成员 捐赠金币 捐赠时间)
     * @throws Exception
     */
    public List<Record> getRecords(int fundIndex) throws Exception {
        List<Record> rList = new ArrayList<Record>();
        // getRecordCount
        int count = contract.getRecordCount(BigInteger.valueOf(fundIndex)).send().intValue();
        for (int recordIndex = 0; recordIndex < count; recordIndex++) {
            // getRecordInfo
            Tuple3<String, BigInteger, BigInteger> rinfo = contract.getRecordInfo(BigInteger.valueOf(fundIndex), BigInteger.valueOf(recordIndex)).send();
            Record record = new Record();
            record.setMember(rinfo.getValue1().toString());
            record.setCoin(new BigInteger(rinfo.getValue2().toString()).divide(Convert.Unit.ETHER.getWeiFactor().toBigInteger()).intValue());
            record.setTime(Integer.parseInt(rinfo.getValue3().toString()));
            rList.add(record);
        }
        Collections.reverse(rList); // 按照记录编号降序排序
        return rList;
    }

    /**
     * 为自己发起众筹
     *
     * @param desc 众筹描述
     * @param goal 众筹目标
     * @throws Exception
     */
    public void raiseFund(String desc, int goal) {
//        contract.raiseFund(desc, goal).sendAsync(); // 异步请求事务 加快返回响应速度
        try {
           TransactionReceipt receipt= contract.raiseFund(desc, BigInteger.valueOf(goal)).send();
            System.out.println(receipt.getContractAddress()+" "+  receipt.getBlockHash()+"  "+  receipt.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 为别人发送金币
     *
     * @param fundIndex 项目编号
     * @param coin      捐赠金币
     * @throws Exception
     */
    public void sendCoin(int fundIndex, int coin) {

        contract.sendCoin(fundIndex, coin).sendAsync(); // 异步请求事务 加快返回响应速度
    }
}