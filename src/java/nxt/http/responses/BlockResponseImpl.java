/*
 * Copyright © 2016-2022 Jelurida IP B.V.
 *
 * See the LICENSE.txt file at the top-level directory of this distribution
 * for licensing information.
 *
 * Unless otherwise agreed in a custom licensing agreement with Jelurida B.V.,
 * no part of this software, including this file, may be copied, modified,
 * propagated, or distributed except according to the terms contained in the
 * LICENSE.txt file.
 *
 * Removal or modification of this copyright notice is prohibited.
 *
 */

package nxt.http.responses;

import nxt.addons.JA;
import nxt.addons.JO;
import nxt.util.Convert;
import org.json.simple.JSONObject;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;

class BlockResponseImpl implements BlockResponse {

    private long blockId;
    private int height;
    private long generatorId;
    private byte[] generatorPublicKey;
    private int timestamp;
    private int numberOfTransactions;
    private long totalFeeFQT;
    private byte version;
    private long baseTarget;
    private BigInteger cumulativeDifficulty;
    private long previousBlockId;
    private long nextBlockId;
    private byte[] payloadHash;
    private byte[] generationSignature;
    private byte[] previousBlockHash;
    private byte[] blockSignature;
    private List<byte[]> parentTransactionFullHashes;
    private List<TransactionResponse> parentTransactions;
    private List<Long> executedPhasedTransactionIds;
    private List<TransactionResponse> executedPhasedTransactions;

    BlockResponseImpl(JSONObject response) {
        this(new JO(response));
    }

    BlockResponseImpl(JO blockJson) {
        blockId = blockJson.getEntityId("block");
        height = blockJson.getInt("height");
        generatorId = blockJson.getEntityId("generator");
        generatorPublicKey = blockJson.parseHexString("generatorPublicKey");
        timestamp = blockJson.getInt("timestamp");
        numberOfTransactions = blockJson.getInt("numberOfTransactions");
        // Check not to break block response generated by GALA
        if (blockJson.isExist("totalFeeFQT")) {
            totalFeeFQT = blockJson.getLong("totalFeeFQT");
        }
        version = blockJson.getByte("version");
        baseTarget = blockJson.getLong("baseTarget");
        cumulativeDifficulty = new BigInteger(blockJson.getString("cumulativeDifficulty"));
        if (blockJson.isExist("previousBlock")) {
            previousBlockId = blockJson.getEntityId("previousBlock");
        }
        if (blockJson.isExist("nextBlock")) {
            nextBlockId = blockJson.getEntityId("nextBlock");
        }
        payloadHash = blockJson.parseHexString("payloadHash");
        generationSignature = blockJson.parseHexString("generationSignature");
        previousBlockHash = blockJson.parseHexString("previousBlockHash");
        blockSignature = blockJson.parseHexString("blockSignature");
        if (blockJson.isExist("transactions")) {
            JA blockTransactions = blockJson.getArray("parentTransactions");
            if (blockTransactions.size() > 0) {
                if (blockTransactions.getObject(0) instanceof String) {
                    parentTransactionFullHashes = blockTransactions.values().stream().map(Convert::parseHexString).collect(Collectors.toList());
                } else {
                    parentTransactions = blockTransactions.objects().stream().map(TransactionResponse::create).collect(Collectors.toList());
                }
            }
        }
        if (blockJson.isExist("transactions")) {
            JA blockTransactions = blockJson.getArray("transactions");
            if (blockTransactions.size() > 0) {
                if (blockTransactions.getObject(0) instanceof String) {
                    parentTransactionFullHashes = blockTransactions.values().stream().map(Convert::parseHexString).collect(Collectors.toList());
                } else {
                    parentTransactions = blockTransactions.objects().stream().map(TransactionResponse::create).collect(Collectors.toList());
                }
            }
        }
        if (blockJson.isExist("executedPhasedTransactions")) {
            List<JO> blockPhasedTransactionsList = blockJson.getJoList("executedPhasedTransactions");
            if (blockPhasedTransactionsList.size() > 0) {
                if (blockPhasedTransactionsList.get(0).isExist("chain")) {
                    executedPhasedTransactionIds = blockPhasedTransactionsList.stream().map(t -> t.getLong("chain")).collect(Collectors.toList());
                } else {
                    executedPhasedTransactions = blockPhasedTransactionsList.stream().map(TransactionResponse::create).collect(Collectors.toList());
                }
            }
        }
    }

    public long getBlockId() {
        return blockId;
    }

    public String getBlock() {
        return Long.toUnsignedString(blockId);
    }

    public int getHeight() {
        return height;
    }

    public long getGeneratorId() {
        return generatorId;
    }

    public String getGenerator() {
        return Long.toUnsignedString(generatorId);
    }

    public String getGeneratorRs() {
        return Convert.rsAccount(generatorId);
    }

    public byte[] getGeneratorPublicKey() {
        return generatorPublicKey;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public int getNumberOfTransactions() {
        return numberOfTransactions;
    }

    public long getTotalFeeFQT() {
        return totalFeeFQT;
    }

    public byte getVersion() {
        return version;
    }

    public long getBaseTarget() {
        return baseTarget;
    }

    public BigInteger getCumulativeDifficulty() {
        return cumulativeDifficulty;
    }

    public long getPreviousBlockId() {
        return previousBlockId;
    }

    public String getPreviousBlock() {
        return Long.toUnsignedString(previousBlockId);
    }

    public long getNextBlockId() {
        return nextBlockId;
    }

    public String getNextBlock() {
        return Long.toUnsignedString(nextBlockId);
    }

    public byte[] getPayloadHash() {
        return payloadHash;
    }

    public byte[] getGenerationSignature() {
        return generationSignature;
    }

    public byte[] getPreviousBlockHash() {
        return previousBlockHash;
    }

    public byte[] getBlockSignature() {
        return blockSignature;
    }

    public List<byte[]> getParentTransactionFullHashes() {
        return parentTransactionFullHashes;
    }

    public List<TransactionResponse> getParentTransactions() {
        return parentTransactions;
    }

    public List<Long> getExecutedPhasedTransactionIds() {
        return executedPhasedTransactionIds;
    }

    public List<TransactionResponse> getExecutedPhasedTransactions() {
        return executedPhasedTransactions;
    }
}