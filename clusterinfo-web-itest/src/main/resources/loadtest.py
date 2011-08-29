# The Grinder 3.2
# HTTP script recorded by TCPProxy at 03-Mar-2009 23:15:25

from net.grinder.script import Test
from net.grinder.script.Grinder import grinder
from net.grinder.plugin.http import HTTPPluginControl, HTTPRequest
from HTTPClient import NVPair
connectionDefaults = HTTPPluginControl.getConnectionDefaults()
httpUtilities = HTTPPluginControl.getHTTPUtilities()

# To use a proxy server, uncomment the next line and set the host and port.
# connectionDefaults.setProxyServer("localhost", 8001)

# These definitions at the top level of the file are evaluated once,
# when the worker process is started.

connectionDefaults.defaultHeaders = \
  ( NVPair('User-Agent', 'Mozilla/5.0 (X11; U; Linux i686; en-GB; rv:1.9.0.6) Gecko/2009020410 Fedora/3.0.6-1.fc9 Firefox/3.0.6'),
    NVPair('Accept-Encoding', 'gzip,deflate'),
    NVPair('Accept-Language', 'en-gb,en;q=0.5'),
    NVPair('Accept-Charset', 'ISO-8859-1,utf-8;q=0.7,*;q=0.7'),
    NVPair('Accept', 'text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8'), )

headers0= \
  ( )

headers1= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller'), )

headers2= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=AllClusterState&mode=Update'), )

headers3= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=AllClusterState&mode=Reset'), )

headers4= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=Request&mode=Update'), )

headers5= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=Request&mode=Reset'), )

headers6= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=Session&mode=Reset'), )

headers7= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=Session&mode=Update'), )

headers8= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=Session&mode=Refresh'), )

headers9= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=JNDILocal&mode=Update'), )

headers10= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=JNDILocal&mode=Reset'), )

headers11= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=JNDILocal&mode=Refresh'), )

headers12= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=JNDIHighAvailability&mode=Update'), )

headers13= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=TreeCache&mode=Update'), )

headers14= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=POJOCache&mode=Update'), )

headers15= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=StatefulSessionEJB&mode=Update'), )

headers16= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=EntityEJBSync&mode=Reset'), )

headers17= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=EntityEJBSync&mode=Update'), )

headers18= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=EntityEJBAsyncJMS&mode=Update'), )

headers19= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=EntityEJBAsyncJMS&mode=Reset'), )

headers20= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=EntityEJBSync&mode=Refresh'), )

headers21= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=StatefulSessionEJB&mode=Reset'), )

headers22= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=StatefulSessionEJB&mode=Refresh'), )

headers23= \
  ( NVPair('Referer', 'http://coe2.saleslab.fab.redhat.com/clusterinfo-web/controller?target=POJOCache&mode=Reset'), )

url0 = 'http://coe2.saleslab.fab.redhat.com:80'
url1 = 'http://safebrowsing.clients.google.com:80'
url2 = 'http://static.cache.l.google.com:80'

# Create an HTTPRequest for each request, then replace the
# reference to the HTTPRequest with an instrumented version.
# You can access the unadorned instance using request101.__target__.
request101 = HTTPRequest(url=url0, headers=headers0)
request101 = Test(101, 'GET web').wrap(request101)

request201 = HTTPRequest(url=url0, headers=headers1)
request201 = Test(201, 'GET web').wrap(request201)

request301 = HTTPRequest(url=url0, headers=headers2)
request301 = Test(301, 'GET web').wrap(request301)

request401 = HTTPRequest(url=url0, headers=headers3)
request401 = Test(401, 'GET web').wrap(request401)

request501 = HTTPRequest(url=url0, headers=headers2)
request501 = Test(501, 'GET web').wrap(request501)

request601 = HTTPRequest(url=url0, headers=headers3)
request601 = Test(601, 'GET web').wrap(request601)

request701 = HTTPRequest(url=url0, headers=headers2)
request701 = Test(701, 'GET web').wrap(request701)

request801 = HTTPRequest(url=url0, headers=headers3)
request801 = Test(801, 'GET web').wrap(request801)

request901 = HTTPRequest(url=url0, headers=headers2)
request901 = Test(901, 'GET web').wrap(request901)

request1001 = HTTPRequest(url=url0, headers=headers3)
request1001 = Test(1001, 'GET web').wrap(request1001)

request1101 = HTTPRequest(url=url0, headers=headers4)
request1101 = Test(1101, 'GET web').wrap(request1101)

request1201 = HTTPRequest(url=url0, headers=headers5)
request1201 = Test(1201, 'GET web').wrap(request1201)

request1301 = HTTPRequest(url=url0, headers=headers6)
request1301 = Test(1301, 'GET web').wrap(request1301)

request1401 = HTTPRequest(url=url0, headers=headers7)
request1401 = Test(1401, 'GET web').wrap(request1401)

request1501 = HTTPRequest(url=url0, headers=headers8)
request1501 = Test(1501, 'GET web').wrap(request1501)

request1601 = HTTPRequest(url=url0, headers=headers9)
request1601 = Test(1601, 'GET web').wrap(request1601)

request1701 = HTTPRequest(url=url0, headers=headers10)
request1701 = Test(1701, 'GET web').wrap(request1701)

request1801 = HTTPRequest(url=url0, headers=headers11)
request1801 = Test(1801, 'GET web').wrap(request1801)

request1901 = HTTPRequest(url=url0, headers=headers12)
request1901 = Test(1901, 'GET web').wrap(request1901)

request2001 = HTTPRequest(url=url0, headers=headers13)
request2001 = Test(2001, 'GET web').wrap(request2001)

request2101 = HTTPRequest(url=url0, headers=headers14)
request2101 = Test(2101, 'GET web').wrap(request2101)

request2201 = HTTPRequest(url=url0, headers=headers15)
request2201 = Test(2201, 'GET web').wrap(request2201)

request2301 = HTTPRequest(url=url0, headers=headers16)
request2301 = Test(2301, 'GET web').wrap(request2301)

request2401 = HTTPRequest(url=url0, headers=headers17)
request2401 = Test(2401, 'GET web').wrap(request2401)

request2501 = HTTPRequest(url=url0, headers=headers18)
request2501 = Test(2501, 'GET web').wrap(request2501)

request2601 = HTTPRequest(url=url0, headers=headers19)
request2601 = Test(2601, 'GET web').wrap(request2601)

request2701 = HTTPRequest(url=url0, headers=headers18)
request2701 = Test(2701, 'GET web').wrap(request2701)

request2801 = HTTPRequest(url=url0, headers=headers19)
request2801 = Test(2801, 'GET web').wrap(request2801)

request2901 = HTTPRequest(url=url0, headers=headers18)
request2901 = Test(2901, 'GET web').wrap(request2901)

request3001 = HTTPRequest(url=url0, headers=headers17)
request3001 = Test(3001, 'GET web').wrap(request3001)

request3101 = HTTPRequest(url=url0, headers=headers20)
request3101 = Test(3101, 'GET web').wrap(request3101)

request3201 = HTTPRequest(url=url1, headers=headers0)
request3201 = Test(3201, 'POST downloads').wrap(request3201)

request3301 = HTTPRequest(url=url2, headers=headers0)
request3301 = Test(3301, 'GET goog-malware-shavar_s_10501-10520.10501.10502-10520:').wrap(request3301)

request3401 = HTTPRequest(url=url0, headers=headers16)
request3401 = Test(3401, 'GET web').wrap(request3401)

request3501 = HTTPRequest(url=url0, headers=headers17)
request3501 = Test(3501, 'GET web').wrap(request3501)

request3601 = HTTPRequest(url=url2, headers=headers0)
request3601 = Test(3601, 'GET goog-malware-shavar_a_9606-9610.9606-9609.9610:').wrap(request3601)

request3701 = HTTPRequest(url=url2, headers=headers0)
request3701 = Test(3701, 'GET goog-phish-shavar_s_36981-36985.36981-36985.:').wrap(request3701)

request3801 = HTTPRequest(url=url2, headers=headers0)
request3801 = Test(3801, 'GET goog-phish-shavar_s_36986-36990.36986-36987.36988-36990:').wrap(request3801)

request3901 = HTTPRequest(url=url2, headers=headers0)
request3901 = Test(3901, 'GET goog-phish-shavar_a_46491-46500.46491-46499.46500:').wrap(request3901)

request4001 = HTTPRequest(url=url0, headers=headers16)
request4001 = Test(4001, 'GET web').wrap(request4001)

request4101 = HTTPRequest(url=url0, headers=headers17)
request4101 = Test(4101, 'GET web').wrap(request4101)

request4201 = HTTPRequest(url=url0, headers=headers15)
request4201 = Test(4201, 'GET web').wrap(request4201)

request4301 = HTTPRequest(url=url0, headers=headers21)
request4301 = Test(4301, 'GET web').wrap(request4301)

request4401 = HTTPRequest(url=url0, headers=headers15)
request4401 = Test(4401, 'GET web').wrap(request4401)

request4501 = HTTPRequest(url=url0, headers=headers21)
request4501 = Test(4501, 'GET web').wrap(request4501)

request4601 = HTTPRequest(url=url0, headers=headers22)
request4601 = Test(4601, 'GET web').wrap(request4601)

request4701 = HTTPRequest(url=url0, headers=headers15)
request4701 = Test(4701, 'GET web').wrap(request4701)

request4801 = HTTPRequest(url=url0, headers=headers23)
request4801 = Test(4801, 'GET web').wrap(request4801)

request4901 = HTTPRequest(url=url0, headers=headers14)
request4901 = Test(4901, 'GET web').wrap(request4901)

request5001 = HTTPRequest(url=url0, headers=headers13)
request5001 = Test(5001, 'GET web').wrap(request5001)

request5101 = HTTPRequest(url=url0, headers=headers12)
request5101 = Test(5101, 'GET web').wrap(request5101)

request5201 = HTTPRequest(url=url0, headers=headers9)
request5201 = Test(5201, 'GET web').wrap(request5201)

request5301 = HTTPRequest(url=url0, headers=headers7)
request5301 = Test(5301, 'GET web').wrap(request5301)


class TestRunner:
  """A TestRunner instance is created for each worker thread."""

  # A method for each recorded page.
  def page1(self):
    """GET web (request 101)."""
    result = request101.GET('/clusterinfo-web/controller')
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page2(self):
    """GET web (request 201)."""
    self.token_mode = \
      'Update'
    result = request201.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page3(self):
    """GET web (request 301)."""
    self.token_mode = \
      'Reset'
    result = request301.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page4(self):
    """GET web (request 401)."""
    self.token_mode = \
      'Update'
    result = request401.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page5(self):
    """GET web (request 501)."""
    self.token_mode = \
      'Reset'
    result = request501.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page6(self):
    """GET web (request 601)."""
    self.token_mode = \
      'Update'
    result = request601.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page7(self):
    """GET web (request 701)."""
    self.token_mode = \
      'Reset'
    result = request701.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page8(self):
    """GET web (request 801)."""
    self.token_mode = \
      'Update'
    result = request801.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page9(self):
    """GET web (request 901)."""
    self.token_mode = \
      'Reset'
    result = request901.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def page10(self):
    """GET web (request 1001)."""
    self.token_target = \
      'Request'
    self.token_mode = \
      'Update'
    result = request1001.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page11(self):
    """GET web (request 1101)."""
    self.token_target = \
      'Request'
    self.token_mode = \
      'Reset'
    result = request1101.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page12(self):
    """GET web (request 1201)."""
    self.token_target = \
      'Session'
    result = request1201.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page13(self):
    """GET web (request 1301)."""
    self.token_target = \
      'Session'
    self.token_mode = \
      'Update'
    result = request1301.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page14(self):
    """GET web (request 1401)."""
    self.token_target = \
      'Session'
    self.token_mode = \
      'Refresh'
    result = request1401.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page15(self):
    """GET web (request 1501)."""
    self.token_target = \
      'JNDILocal'
    self.token_mode = \
      'Update'
    result = request1501.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page16(self):
    """GET web (request 1601)."""
    self.token_target = \
      'JNDILocal'
    self.token_mode = \
      'Reset'
    result = request1601.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page17(self):
    """GET web (request 1701)."""
    self.token_target = \
      'JNDILocal'
    self.token_mode = \
      'Refresh'
    result = request1701.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page18(self):
    """GET web (request 1801)."""
    self.token_target = \
      'JNDIHighAvailability'
    self.token_mode = \
      'Update'
    result = request1801.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page19(self):
    """GET web (request 1901)."""
    self.token_target = \
      'TreeCache'
    result = request1901.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page20(self):
    """GET web (request 2001)."""
    self.token_target = \
      'POJOCache'
    result = request2001.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page21(self):
    """GET web (request 2101)."""
    self.token_target = \
      'StatefulSessionEJB'
    result = request2101.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page22(self):
    """GET web (request 2201)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Reset'
    result = request2201.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page23(self):
    """GET web (request 2301)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Update'
    result = request2301.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page24(self):
    """GET web (request 2401)."""
    self.token_target = \
      'EntityEJBAsyncJMS'
    result = request2401.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page25(self):
    """GET web (request 2501)."""
    self.token_target = \
      'EntityEJBAsyncJMS'
    self.token_mode = \
      'Reset'
    result = request2501.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page26(self):
    """GET web (request 2601)."""
    self.token_target = \
      'EntityEJBAsyncJMS'
    self.token_mode = \
      'Update'
    result = request2601.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page27(self):
    """GET web (request 2701)."""
    self.token_target = \
      'EntityEJBAsyncJMS'
    self.token_mode = \
      'Reset'
    result = request2701.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page28(self):
    """GET web (request 2801)."""
    self.token_target = \
      'EntityEJBAsyncJMS'
    self.token_mode = \
      'Update'
    result = request2801.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page29(self):
    """GET web (request 2901)."""
    self.token_target = \
      'EntityEJBSync'
    result = request2901.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page30(self):
    """GET web (request 3001)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Refresh'
    result = request3001.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page31(self):
    """GET web (request 3101)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Reset'
    result = request3101.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page32(self):
    """POST downloads (request 3201)."""
    self.token_client = \
      'navclient-auto-ffox'
    self.token_appver = \
      '3.0.6'
    self.token_pver = \
      '2.2'
    self.token_wrkey = \
      'AKEgNits5gLRwP2arF3edhdyItcuvqxbje7XJb__pAX26PWwKyz7M4NpSoU9_H5bYE60X3XpdN0IE4LYPDJ2XIrjjkcG-184EA=='
    result = request3201.POST('/safebrowsing/downloads' +
      '?client=' +
      self.token_client +
      '&appver=' +
      self.token_appver +
      '&pver=' +
      self.token_pver +
      '&wrkey=' +
      self.token_wrkey,
      '''goog-malware-shavar;a:7975-9607:s:8712-10500:mac\ngoog-phish-shavar;a:37560-46495:s:31858-36984:mac\n''',
      ( NVPair('Content-Type', 'text/plain'), ))

    return result

  def page33(self):
    """GET goog-malware-shavar_s_10501-10520.10501.10502-10520: (request 3301)."""
    result = request3301.GET('/safebrowsing/rd/goog-malware-shavar_s_10501-10520.10501.10502-10520:')

    return result

  def page34(self):
    """GET web (request 3401)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Update'
    result = request3401.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page35(self):
    """GET web (request 3501)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Reset'
    result = request3501.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page36(self):
    """GET goog-malware-shavar_a_9606-9610.9606-9609.9610: (request 3601)."""
    result = request3601.GET('/safebrowsing/rd/goog-malware-shavar_a_9606-9610.9606-9609.9610:')

    return result

  def page37(self):
    """GET goog-phish-shavar_s_36981-36985.36981-36985.: (request 3701)."""
    result = request3701.GET('/safebrowsing/rd/goog-phish-shavar_s_36981-36985.36981-36985.:')

    return result

  def page38(self):
    """GET goog-phish-shavar_s_36986-36990.36986-36987.36988-36990: (request 3801)."""
    result = request3801.GET('/safebrowsing/rd/goog-phish-shavar_s_36986-36990.36986-36987.36988-36990:')

    return result

  def page39(self):
    """GET goog-phish-shavar_a_46491-46500.46491-46499.46500: (request 3901)."""
    result = request3901.GET('/safebrowsing/rd/goog-phish-shavar_a_46491-46500.46491-46499.46500:')

    return result

  def page40(self):
    """GET web (request 4001)."""
    self.token_target = \
      'EntityEJBSync'
    self.token_mode = \
      'Update'
    result = request4001.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page41(self):
    """GET web (request 4101)."""
    self.token_target = \
      'StatefulSessionEJB'
    result = request4101.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page42(self):
    """GET web (request 4201)."""
    self.token_target = \
      'StatefulSessionEJB'
    self.token_mode = \
      'Reset'
    result = request4201.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page43(self):
    """GET web (request 4301)."""
    self.token_target = \
      'StatefulSessionEJB'
    self.token_mode = \
      'Update'
    result = request4301.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page44(self):
    """GET web (request 4401)."""
    self.token_target = \
      'StatefulSessionEJB'
    self.token_mode = \
      'Reset'
    result = request4401.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page45(self):
    """GET web (request 4501)."""
    self.token_target = \
      'StatefulSessionEJB'
    self.token_mode = \
      'Refresh'
    result = request4501.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page46(self):
    """GET web (request 4601)."""
    self.token_target = \
      'StatefulSessionEJB'
    self.token_mode = \
      'Update'
    result = request4601.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page47(self):
    """GET web (request 4701)."""
    self.token_target = \
      'POJOCache'
    self.token_mode = \
      'Reset'
    result = request4701.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page48(self):
    """GET web (request 4801)."""
    self.token_target = \
      'POJOCache'
    self.token_mode = \
      'Update'
    result = request4801.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page49(self):
    """GET web (request 4901)."""
    self.token_target = \
      'TreeCache'
    result = request4901.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page50(self):
    """GET web (request 5001)."""
    self.token_target = \
      'JNDIHighAvailability'
    result = request5001.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page51(self):
    """GET web (request 5101)."""
    self.token_target = \
      'JNDILocal'
    result = request5101.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page52(self):
    """GET web (request 5201)."""
    self.token_target = \
      'Session'
    result = request5201.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 10 different values for token_target found in response, using the first one.
    self.token_target = \
      httpUtilities.valueFromHiddenInput('target') # 'AllClusterState'

    return result

  def page53(self):
    """GET web (request 5301)."""
    self.token_mode = \
      'Reset'
    result = request5301.GET('/clusterinfo-web/controller' +
      '?target=' +
      self.token_target +
      '&mode=' +
      self.token_mode)
    # 9 different values for token_target found in response; the first matched
    # the last known value of token_target - don't update the variable.

    return result

  def __call__(self):
    """This method is called for every run performed by the worker thread."""
    self.page1()      # GET web (request 101)

    grinder.sleep(1000)
    self.page2()      # GET web (request 201)

    grinder.sleep(1000)
    self.page3()      # GET web (request 301)

    grinder.sleep(1000)
    self.page4()      # GET web (request 401)

    grinder.sleep(1000)
    self.page5()      # GET web (request 501)

    grinder.sleep(1000)
    self.page6()      # GET web (request 601)

    grinder.sleep(1000)
    self.page7()      # GET web (request 701)

    grinder.sleep(1000)
    self.page8()      # GET web (request 801)

    grinder.sleep(1000)
    self.page9()      # GET web (request 901)

    grinder.sleep(1000)
    self.page10()     # GET web (request 1001)

    grinder.sleep(1000)
    self.page11()     # GET web (request 1101)

    grinder.sleep(1000)
    self.page12()     # GET web (request 1201)

    grinder.sleep(1000)
    self.page13()     # GET web (request 1301)

    grinder.sleep(1000)
    self.page14()     # GET web (request 1401)

    grinder.sleep(1000)
    self.page15()     # GET web (request 1501)

    grinder.sleep(1000)
    self.page16()     # GET web (request 1601)

    grinder.sleep(1000)
    self.page17()     # GET web (request 1701)

    grinder.sleep(1000)
    self.page18()     # GET web (request 1801)

    grinder.sleep(1000)
    self.page19()     # GET web (request 1901)

    grinder.sleep(1000)
    self.page20()     # GET web (request 2001)

    grinder.sleep(1000)
    self.page21()     # GET web (request 2101)

    grinder.sleep(1000)
    self.page22()     # GET web (request 2201)

    grinder.sleep(1000)
    self.page23()     # GET web (request 2301)

    grinder.sleep(1000)
    self.page24()     # GET web (request 2401)

    grinder.sleep(1000)
    self.page25()     # GET web (request 2501)

    grinder.sleep(1000)
    self.page26()     # GET web (request 2601)

    grinder.sleep(1000)
    self.page27()     # GET web (request 2701)

    grinder.sleep(1000)
    self.page28()     # GET web (request 2801)

    grinder.sleep(1000)
    self.page29()     # GET web (request 2901)

    grinder.sleep(1000)
    self.page30()     # GET web (request 3001)

    grinder.sleep(1000)
    self.page31()     # GET web (request 3101)

#    grinder.sleep(1000)
#    self.page32()     # POST downloads (request 3201)

#    grinder.sleep(1000)
#    self.page33()     # GET goog-malware-shavar_s_10501-10520.10501.10502-10520: (request 3301)

    grinder.sleep(1000)
    self.page34()     # GET web (request 3401)

    grinder.sleep(1000)
    self.page35()     # GET web (request 3501)
#    self.page36()     # GET goog-malware-shavar_a_9606-9610.9606-9609.9610: (request 3601)

#    grinder.sleep(1000)
#    self.page37()     # GET goog-phish-shavar_s_36981-36985.36981-36985.: (request 3701)

#    grinder.sleep(1000)
#    self.page38()     # GET goog-phish-shavar_s_36986-36990.36986-36987.36988-36990: (request 3801)

#    grinder.sleep(1000)
#    self.page39()     # GET goog-phish-shavar_a_46491-46500.46491-46499.46500: (request 3901)

    grinder.sleep(1000)
    self.page40()     # GET web (request 4001)

    grinder.sleep(1000)
    self.page41()     # GET web (request 4101)

    grinder.sleep(1000)
    self.page42()     # GET web (request 4201)

    grinder.sleep(1000)
    self.page43()     # GET web (request 4301)

    grinder.sleep(1000)
    self.page44()     # GET web (request 4401)

    grinder.sleep(1000)
    self.page45()     # GET web (request 4501)

    grinder.sleep(1000)
    self.page46()     # GET web (request 4601)

    grinder.sleep(1000)
    self.page47()     # GET web (request 4701)

    grinder.sleep(1000)
    self.page48()     # GET web (request 4801)

    grinder.sleep(1000)
    self.page49()     # GET web (request 4901)

    grinder.sleep(1000)
    self.page50()     # GET web (request 5001)

    grinder.sleep(1000)
    self.page51()     # GET web (request 5101)

    grinder.sleep(1000)
    self.page52()     # GET web (request 5201)

    grinder.sleep(1000)
    self.page53()     # GET web (request 5301)


def instrumentMethod(test, method_name, c=TestRunner):
  """Instrument a method with the given Test."""
  unadorned = getattr(c, method_name)
  import new
  method = new.instancemethod(test.wrap(unadorned), None, c)
  setattr(c, method_name, method)

# Replace each method with an instrumented version.
# You can call the unadorned method using self.page1.__target__().
instrumentMethod(Test(100, 'Page 1'), 'page1')
instrumentMethod(Test(200, 'Page 2'), 'page2')
instrumentMethod(Test(300, 'Page 3'), 'page3')
instrumentMethod(Test(400, 'Page 4'), 'page4')
instrumentMethod(Test(500, 'Page 5'), 'page5')
instrumentMethod(Test(600, 'Page 6'), 'page6')
instrumentMethod(Test(700, 'Page 7'), 'page7')
instrumentMethod(Test(800, 'Page 8'), 'page8')
instrumentMethod(Test(900, 'Page 9'), 'page9')
instrumentMethod(Test(1000, 'Page 10'), 'page10')
instrumentMethod(Test(1100, 'Page 11'), 'page11')
instrumentMethod(Test(1200, 'Page 12'), 'page12')
instrumentMethod(Test(1300, 'Page 13'), 'page13')
instrumentMethod(Test(1400, 'Page 14'), 'page14')
instrumentMethod(Test(1500, 'Page 15'), 'page15')
instrumentMethod(Test(1600, 'Page 16'), 'page16')
instrumentMethod(Test(1700, 'Page 17'), 'page17')
instrumentMethod(Test(1800, 'Page 18'), 'page18')
instrumentMethod(Test(1900, 'Page 19'), 'page19')
instrumentMethod(Test(2000, 'Page 20'), 'page20')
instrumentMethod(Test(2100, 'Page 21'), 'page21')
instrumentMethod(Test(2200, 'Page 22'), 'page22')
instrumentMethod(Test(2300, 'Page 23'), 'page23')
instrumentMethod(Test(2400, 'Page 24'), 'page24')
instrumentMethod(Test(2500, 'Page 25'), 'page25')
instrumentMethod(Test(2600, 'Page 26'), 'page26')
instrumentMethod(Test(2700, 'Page 27'), 'page27')
instrumentMethod(Test(2800, 'Page 28'), 'page28')
instrumentMethod(Test(2900, 'Page 29'), 'page29')
instrumentMethod(Test(3000, 'Page 30'), 'page30')
instrumentMethod(Test(3100, 'Page 31'), 'page31')
instrumentMethod(Test(3200, 'Page 32'), 'page32')
instrumentMethod(Test(3300, 'Page 33'), 'page33')
instrumentMethod(Test(3400, 'Page 34'), 'page34')
instrumentMethod(Test(3500, 'Page 35'), 'page35')
instrumentMethod(Test(3600, 'Page 36'), 'page36')
instrumentMethod(Test(3700, 'Page 37'), 'page37')
instrumentMethod(Test(3800, 'Page 38'), 'page38')
instrumentMethod(Test(3900, 'Page 39'), 'page39')
instrumentMethod(Test(4000, 'Page 40'), 'page40')
instrumentMethod(Test(4100, 'Page 41'), 'page41')
instrumentMethod(Test(4200, 'Page 42'), 'page42')
instrumentMethod(Test(4300, 'Page 43'), 'page43')
instrumentMethod(Test(4400, 'Page 44'), 'page44')
instrumentMethod(Test(4500, 'Page 45'), 'page45')
instrumentMethod(Test(4600, 'Page 46'), 'page46')
instrumentMethod(Test(4700, 'Page 47'), 'page47')
instrumentMethod(Test(4800, 'Page 48'), 'page48')
instrumentMethod(Test(4900, 'Page 49'), 'page49')
instrumentMethod(Test(5000, 'Page 50'), 'page50')
instrumentMethod(Test(5100, 'Page 51'), 'page51')
instrumentMethod(Test(5200, 'Page 52'), 'page52')
instrumentMethod(Test(5300, 'Page 53'), 'page53')
