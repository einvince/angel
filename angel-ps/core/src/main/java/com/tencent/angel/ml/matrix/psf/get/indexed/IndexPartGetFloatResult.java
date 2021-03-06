/*
 * Tencent is pleased to support the open source community by making Angel available.
 *
 * Copyright (C) 2017-2018 THL A29 Limited, a Tencent company. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in 
 * compliance with the License. You may obtain a copy of the License at
 *
 * https://opensource.org/licenses/Apache-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 *
 */


package com.tencent.angel.ml.matrix.psf.get.indexed;

import com.tencent.angel.PartitionKey;
import com.tencent.angel.ps.server.data.request.ValueType;
import io.netty.buffer.ByteBuf;

public class IndexPartGetFloatResult extends IndexPartGetResult {
  private float[] values;

  public IndexPartGetFloatResult(PartitionKey partKey, float[] values) {
    super(partKey);
    this.values = values;
  }

  public IndexPartGetFloatResult() {
    this(null, null);
  }

  @Override public void serialize(ByteBuf buf) {
    super.serialize(buf);
    buf.writeInt(values.length);
    for (int i = 0; i < values.length; i++) {
      buf.writeFloat(values[i]);
    }
  }

  @Override public void deserialize(ByteBuf buf) {
    super.deserialize(buf);
    int len = buf.readInt();
    values = new float[len];
    for (int i = 0; i < len; i++) {
      values[i] = buf.readFloat();
    }
  }

  @Override public int bufferLen() {
    return super.bufferLen() + 4 + values.length * 4;
  }

  @Override public ValueType getValueType() {
    return ValueType.FLOAT;
  }

  public float[] getValues() {
    return values;
  }
}
