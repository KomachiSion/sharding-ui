/*
 * Copyright 2016-2018 shardingsphere.io.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * </p>
 */

package io.shardingsphere.shardingui.web.advice;

import io.shardingsphere.shardingui.common.exception.ShardingUIException;
import io.shardingsphere.shardingui.web.response.ResponseResult;
import io.shardingsphere.shardingui.web.response.ResponseResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Global exception handler.
 * 
 * @author chenqingyang
 */
@Slf4j
@ControllerAdvice
public final class GlobalExceptionHandler {
    
    /**
     * Handle exception.
     * 
     * @param ex exception
     * @return response result
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseResult<?> handleException(final Exception ex) {
        log.error("controller error", ex);
        if (ex instanceof IllegalArgumentException) {
            return ResponseResultUtil.handleIllegalArgumentException(ex.getMessage());
        } else if (ex instanceof ShardingUIException) {
            return ResponseResultUtil.handleShardingUIException((ShardingUIException) ex);
        }
        return ResponseResultUtil.handleUncaughtException(ex.getMessage());
    }
}
