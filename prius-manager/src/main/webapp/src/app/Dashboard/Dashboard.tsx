/**
 *  Copyright 2023 Javi Roman
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
import * as React from 'react';
import {
  EmptyState,
  EmptyStateVariant,
  PageSection,
  Spinner,
  Title} from '@patternfly/react-core';
import Cookies from 'js-cookie';
import {AppLogin} from "@app/AppLogin/AppLogin";

const Dashboard: React.FunctionComponent = () => {
  const [isLoged, setIsLoged] = React.useState(false);
  const [isLoading, setIsLoading] = React.useState(false);

  const onHandleLogin = (value) => {
    setIsLoged(value);
  }

  React.useEffect(() => {
    let value = {};
    value = Cookies.getJSON('prius-auth');
    if (value) {
      setIsLoged(true);
    } else {
      setIsLoading(true);
      setIsLoged(false);
      location.reload();
    }
  }, []);

  return (
    isLoading ?
      <EmptyState variant={EmptyStateVariant.full}>
        <Spinner/>
      </EmptyState>
    : ! isLoged ?
        <AppLogin handleLogin={onHandleLogin}/>
    :
      <>

        <PageSection>
          <Title headingLevel="h1" size="lg">
            Dashboard Page Title
          </Title>
        </PageSection>

      </>
  )
}

export { Dashboard };
