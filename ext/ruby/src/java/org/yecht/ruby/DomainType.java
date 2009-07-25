package org.yecht.ruby;

import org.jruby.RubyObject;
import org.jruby.anno.JRubyMethod;
import org.jruby.runtime.builtin.IRubyObject;

public class DomainType {
    // syck_domaintype_initialize
    @JRubyMethod
    public static IRubyObject initialize(IRubyObject self, IRubyObject domain, IRubyObject type_id, IRubyObject val) {
        ((RubyObject)self).fastSetInternalVariable("@domain", domain);
        ((RubyObject)self).fastSetInternalVariable("@type_id", type_id);
        ((RubyObject)self).fastSetInternalVariable("@value", val);
        return self;
    }
}
