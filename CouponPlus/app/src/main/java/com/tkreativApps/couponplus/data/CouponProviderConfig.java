package com.tkreativApps.couponplus.data;

import ckm.simple.sql_provider.UpgradeScript;
import ckm.simple.sql_provider.annotation.ProviderConfig;
import ckm.simple.sql_provider.annotation.SimpleSQLConfig;

@SimpleSQLConfig(
        name="CouponProvider",
        authority = "com.tkreativApps.couponplus.authority",
        database = "couponplus.db",
        version = 1
)
public class CouponProviderConfig implements ProviderConfig{
    @Override
    public UpgradeScript[] getUpdateScripts() {
        return new UpgradeScript[0];
    }
}
